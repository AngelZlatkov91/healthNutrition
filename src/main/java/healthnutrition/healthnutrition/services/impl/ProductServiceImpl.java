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
    // add product for admin
    public void addProduct(ProductCreateDTO productCreateDTO){
        this.productRepository.save(map(productCreateDTO));

    }



    @Override
    // get all product by search key
    public List<ProductDetailsDTO> getAllProducts(String searchKey) {
        Pageable pageable = PageRequest.of(0,10);
        if (searchKey.equals("")) {
            return productRepository.findAll(pageable).map(this::mapAsDetails).toList();
        } else {
            List<ProductDetailsDTO> collect = productRepository.findByNameContainingIgnoreCaseOrBrantBrandContainingIgnoreCaseOrTypeTypeContainingIgnoreCase(
                    searchKey, searchKey, searchKey, pageable).stream().map(this::mapAsDetails).collect(Collectors.toList());
            searchKey = "";
            return collect;
        }
    }

    @Override
    // get product details with uuid
    public ProductDetailsDTO getProductDetails(UUID uuid) {
        Product byUuid = productRepository.findByUuid(uuid);
        return mapAsDetails(byUuid);
    }

    @Override
    @Transactional
    // delete product for admin with uuid
    public void deleteProduct(UUID uuid) {
        this.productRepository.deleteByUuid(uuid);
    }

    @Override
    // select product quantity for scheduling
    public String sellerProductQuantity() {
        return this.productInCartRepositories.QuantitySellerProduct();
    }


    // map product entity to productDetailsDTO
    private ProductDetailsDTO mapAsDetails(Product product) {
        ProductDetailsDTO productDetailsDTO = this.mapper.map(product,ProductDetailsDTO.class);
        productDetailsDTO.setId(product.getUuid().toString());
        productDetailsDTO.setBrant(product.getBrant().getBrand());
        productDetailsDTO.setType(product.getType().getType());
        return productDetailsDTO;
    }



    // create product from ProductCreateDTO
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
