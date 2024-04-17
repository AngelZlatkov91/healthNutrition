package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.ProductDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    void addProduct(ProductCreateDTO productCreateDTO);


    Page<ProductDetailsDTO> getAllProducts(Pageable pageable);

    ProductDetailsDTO getProductDetails(UUID uuid);

}
