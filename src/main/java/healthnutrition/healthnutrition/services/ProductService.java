package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.ProductDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void addProduct(ProductCreateDTO productCreateDTO);


    List<ProductDetailsDTO> getAllProducts(String searchKey);

    ProductDetailsDTO getProductDetails(UUID uuid);

    void deleteProduct(UUID uuid);

    String sellerProductQuantity();
}
