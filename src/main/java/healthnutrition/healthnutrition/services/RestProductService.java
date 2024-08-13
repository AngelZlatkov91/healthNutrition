package healthnutrition.healthnutrition.services;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface RestProductService {
    List<ProductDetailsDTO> getAllProducts();

    ProductDetailsDTO getProductById(String name);

    void deleteProduct(String name);

    void addProduct(ProductCreateDTO productCreateDTO);

}
