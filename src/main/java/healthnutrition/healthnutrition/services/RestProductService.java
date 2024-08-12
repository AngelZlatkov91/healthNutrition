package healthnutrition.healthnutrition.services;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import java.util.List;
import java.util.Optional;


public interface RestProductService {
    List<ProductDetailsDTO> getAllProducts();

    ProductDetailsDTO getProductById(Long id);

    void deleteProduct(Long id);

    Long addProduct(ProductCreateDTO productCreateDTO);

}
