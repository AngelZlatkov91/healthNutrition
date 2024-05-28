package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.ProductDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void addProduct(ProductCreateDTO productCreateDTO);


    List<ProductDetailsDTO> getAllProducts(String searchKey);

    ProductDetailsDTO getProductDetails(UUID uuid);

}
