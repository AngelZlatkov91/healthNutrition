package healthnutrition.healthnutrition.web.AdminController;

import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
public class RestControllerGetProducts {
           // TODO
    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerGetProducts.class);

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

    @GetMapping("/api/get/product/{uuid}")
    public ResponseEntity<ProductDetailsDTO> getById(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity
                .ok(productService.getProductDetails(uuid));
    }

    @DeleteMapping("/api/remove/product/{uuid}")
    public ResponseEntity<ProductDetailsDTO> deleteById(@PathVariable("uuid") UUID uuid) {
        productService.deleteProduct(uuid);
        return ResponseEntity
                .noContent()
                .build();
    }


    @PostMapping("/api/create/product")
    public ResponseEntity<ProductCreateDTO> createOffer(
            @RequestBody ProductCreateDTO productCreateDTO
    ) {
        LOGGER.info("Going to create an offer {}", productCreateDTO);
          this.productService.addProduct(productCreateDTO);


        return ResponseEntity.
                created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{name}")
                                .buildAndExpand(productCreateDTO.getName())
                                .toUri()
                ).body(productCreateDTO);
    }


}
