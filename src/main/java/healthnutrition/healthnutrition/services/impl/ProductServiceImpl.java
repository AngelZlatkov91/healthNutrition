package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import healthnutrition.healthnutrition.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TypeRepository typeRepository;
    private final BrandRepository brandRepository;

    public ProductServiceImpl(ProductRepository productRepository, TypeRepository typeRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.typeRepository = typeRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public void addProduct(ProductCreateDTO productCreateDTO){

        this.productRepository.save(map(productCreateDTO));
    }



    @Override
    public Page<ProductDetailsDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductServiceImpl::mapSummary);
    }

    @Override
    public ProductDetailsDTO getProductDetails(UUID uuid) {
        Product byUuid = productRepository.findByUuid(uuid);
        return mapAsDetails(byUuid);
    }


    private ProductDetailsDTO mapAsDetails(Product product) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        productDetailsDTO.setId(product.getUuid().toString());
        productDetailsDTO.setDescription(product.getDescription());
        productDetailsDTO.setPrice(product.getPrice());
        productDetailsDTO.setName(product.getName());
        productDetailsDTO.setImageUrl(product.getImageUrl());
        productDetailsDTO.setBrant(product.getBrant().getBrand());
        productDetailsDTO.setType(product.getType().getType());
        return productDetailsDTO;
    }

    private static ProductDetailsDTO mapSummary(Product product) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        productDetailsDTO.setId(product.getUuid().toString());
        productDetailsDTO.setName(product.getName());
        productDetailsDTO.setPrice(product.getPrice());
        productDetailsDTO.setImageUrl(product.getImageUrl());
        productDetailsDTO.setType(product.getType().getType());
        productDetailsDTO.setBrant(product.getBrant().getBrand());
        return productDetailsDTO;
    }

    private Product map(ProductCreateDTO productCreateDTO) {
            Product product = new Product();
            Optional<TypeProduct> byType = this.typeRepository.findByType(productCreateDTO.type());
            Optional<BrandProduct> byBrand = this.brandRepository.findByBrand(productCreateDTO.brand());
            product.setUuid(UUID.randomUUID());
            product.setName(productCreateDTO.name());
            product.setImageUrl(productCreateDTO.imageUrl());
            product.setDescription(productCreateDTO.description());
            product.setAvailability(productCreateDTO.availability());
            product.setPrice(productCreateDTO.price());
            product.setBrant(byBrand.get());
            product.setType(byType.get());
            return product;
    }



}
