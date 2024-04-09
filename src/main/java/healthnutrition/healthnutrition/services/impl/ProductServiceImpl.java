package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.ProductCreateDTO;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductCreateDTO productCreateDTO) {
        this.productRepository.save(map(productCreateDTO));
    }

    private Product map(ProductCreateDTO productCreateDTO) {
        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setDescription(productCreateDTO.getDescription());
        product.setAvailability(productCreateDTO.getAvailability());
        product.setPrice(productCreateDTO.getPrice());
        product.setImageUrl(productCreateDTO.getImageUrl());
        return product;
    }


}
