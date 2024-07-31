package healthnutrition.healthnutrition.services;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestProductService {
    List<ProductDetailsDTO> getAllProducts();

    Optional<ProductDetailsDTO> getProductById(UUID uuid);

    void deleteProduct(UUID uuid);

    Long addProduct(ProductCreateDTO productCreateDTO);

}
