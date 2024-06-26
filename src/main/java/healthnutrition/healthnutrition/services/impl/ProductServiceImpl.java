package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.ProductInCartRepositories;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import healthnutrition.healthnutrition.services.ProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TypeRepository typeRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;
    private final ProductInCartRepositories productInCartRepositories;

    public ProductServiceImpl(ProductRepository productRepository, TypeRepository typeRepository, BrandRepository brandRepository, ModelMapper mapper, ProductInCartRepositories productInCartRepositories) {
        this.productRepository = productRepository;
        this.typeRepository = typeRepository;
        this.brandRepository = brandRepository;
        this.mapper = mapper;
        this.productInCartRepositories = productInCartRepositories;
    }

    @Override
    public void addProduct(ProductCreateDTO productCreateDTO){

        this.productRepository.save(map(productCreateDTO));
    }



    @Override
    public List<ProductDetailsDTO> getAllProducts(String searchKey) {
        Pageable pageable = PageRequest.of(0,10);
        if (searchKey.equals("")) {
            return productRepository.findAll(pageable).map(this::mapSummary).toList();
        } else {
            List<ProductDetailsDTO> collect = productRepository.findByNameContainingIgnoreCaseOrBrantBrandContainingIgnoreCaseOrTypeTypeContainingIgnoreCase(
                    searchKey, searchKey, searchKey, pageable).stream().map(this::mapSummary).collect(Collectors.toList());
            searchKey = "";
            return collect;
        }
    }

    @Override
    public ProductDetailsDTO getProductDetails(UUID uuid) {
        Product byUuid = productRepository.findByUuid(uuid);
        return mapAsDetails(byUuid);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID uuid) {
        this.productRepository.deleteByUuid(uuid);
    }

    @Override
    public String sellerProductQuantity() {
        return this.productInCartRepositories.QuantitySellerProduct();
    }


    private ProductDetailsDTO mapAsDetails(Product product) {
        ProductDetailsDTO productDetailsDTO = this.mapper.map(product,ProductDetailsDTO.class);
        productDetailsDTO.setId(product.getUuid().toString());
//        productDetailsDTO.setDescription(product.getDescription());
//        productDetailsDTO.setPrice(product.getPrice());
//        productDetailsDTO.setName(product.getName());
//        productDetailsDTO.setImageUrl(product.getImageUrl());
        productDetailsDTO.setBrant(product.getBrant().getBrand());
        productDetailsDTO.setType(product.getType().getType());
        return productDetailsDTO;
    }

    private ProductDetailsDTO mapSummary(Product product) {
        ProductDetailsDTO productDetailsDTO = this.mapper.map(product,ProductDetailsDTO.class);
        productDetailsDTO.setId(product.getUuid().toString());
//        productDetailsDTO.setName(product.getName());
//        productDetailsDTO.setPrice(product.getPrice());
//        productDetailsDTO.setImageUrl(product.getImageUrl());
        productDetailsDTO.setType(product.getType().getType());
        productDetailsDTO.setBrant(product.getBrant().getBrand());
        return productDetailsDTO;
    }

    private Product map(ProductCreateDTO productCreateDTO) {
            Product product = this.mapper.map(productCreateDTO,Product.class);
            Optional<TypeProduct> byType = this.typeRepository.findByType(productCreateDTO.getType());
            Optional<BrandProduct> byBrand = this.brandRepository.findByBrand(productCreateDTO.getBrand());
            product.setUuid(UUID.randomUUID());
            product.setBrant(byBrand.get());
            product.setType(byType.get());
            return product;
    }





}
