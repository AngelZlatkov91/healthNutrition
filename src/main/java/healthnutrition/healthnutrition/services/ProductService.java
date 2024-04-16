package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.ProductDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    void addProduct(ProductCreateDTO productCreateDTO);


    Page<ProductDetailsDTO> getAllProducts(Pageable pageable);

}
