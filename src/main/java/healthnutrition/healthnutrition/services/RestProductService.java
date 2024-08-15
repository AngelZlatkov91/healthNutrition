package healthnutrition.healthnutrition.services;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductEditPrice;

import java.util.List;


public interface RestProductService {
    List<ProductDetailsDTO> getAllProducts();

    ProductDetailsDTO getProductByName(String name);

    void deleteProduct(String name);

    void addProduct(ProductCreateDTO productCreateDTO);

    void editPrice(ProductEditPrice productEditPrice);

    List<ProductDetailsDTO> getAllProducts(String searchKey);

    ProductInCartDTO findProduct(String name);


}
