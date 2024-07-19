package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestControllerGetProducts {

    private final ProductService productService;

    public RestControllerGetProducts(ProductService productService) {
        this.productService = productService;
    }
    // very simple rest controller  to do more specific
    @GetMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDetailsDTO>> getAllOffers() {
     //TODO
        return ResponseEntity.ok(
                productService.getAllProducts("")
        );
    }
}
