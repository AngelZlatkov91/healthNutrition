package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import healthnutrition.healthnutrition.services.RestProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestProductServiceImpl implements RestProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final TypeRepository typeRepository;
    private final BrandRepository brandRepository;

    public RestProductServiceImpl(ProductRepository productRepository, ModelMapper mapper, TypeRepository typeRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.typeRepository = typeRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<ProductDetailsDTO> getAllProducts() {
        return productRepository.findAll(Pageable.unpaged()).map(RestProductServiceImpl::mapAsDetails).toList();

    }

    @Override
    public Optional<ProductDetailsDTO> getProductById(Long id) {
        Optional<Product> byId = this.productRepository.findById(id);

        return  productRepository.findById(id).map(ProductServiceImpl::mapAsDetails);
    }

    @Override
    public void deleteProduct(Long id) {
         this.productRepository.deleteById(id);
    }

    @Override
    public Long addProduct(ProductCreateDTO productCreateDTO) {
        Product product = productRepository.save(map(productCreateDTO));
        return product.getId();
    }

    private Product map(ProductCreateDTO productCreateDTO) {
        Product product = this.mapper.map(productCreateDTO, Product.class);
        Optional<TypeProduct> byType = typeRepository.findByType(productCreateDTO.getType());
        Optional<BrandProduct> byBrand = brandRepository.findByBrand(productCreateDTO.getBrand());
        product.setUuid(UUID.randomUUID());
        product.setType(byType.get());
        product.setBrant(byBrand.get());
        return product;
    }

    private static ProductDetailsDTO mapAsDetails(Product product) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        productDetailsDTO.setName(product.getName());
        productDetailsDTO.setDescription(product.getDescription());
        productDetailsDTO.setPrice(product.getPrice());
        productDetailsDTO.setImageUrl(product.getImageUrl());
        productDetailsDTO.setId(product.getUuid().toString());
        productDetailsDTO.setBrant(product.getBrant().getBrand());
        productDetailsDTO.setType(product.getType().getType());
        return  productDetailsDTO;
    }
}
