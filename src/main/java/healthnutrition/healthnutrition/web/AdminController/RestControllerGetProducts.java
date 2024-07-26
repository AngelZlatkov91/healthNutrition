package healthnutrition.healthnutrition.web.AdminController;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.RestProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/products/")
public class RestControllerGetProducts {
    // very simple rest controller  to do more specific
           // TODO
    private final RestProductService restProductService;

    public RestControllerGetProducts(RestProductService restProductService) {
        this.restProductService = restProductService;
    }


    // get all products permit all
    @GetMapping
    public ResponseEntity<List<ProductDetailsDTO>> getAllOffers() {

        return ResponseEntity.ok(
                restProductService.getAllProducts()
        );
    }
    // get product by uuid permit all
    @GetMapping(value = "/get/product/{id}")
    public ResponseEntity<ProductDetailsDTO> getById(@PathVariable("id") Long id) {
       Optional<ProductDetailsDTO> productDetails = restProductService.getProductById(id);
        return productDetails.(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // remove product by uuid permit only admin

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ProductDetailsDTO> deleteById(@PathVariable("id") Long id) {
        restProductService.deleteProduct(id);
        return ResponseEntity
                .noContent()
                .build();
    }
    // create product  permit only admin
    @PostMapping("/create")
    public ResponseEntity<ProductCreateDTO> createOffer(
            @RequestBody ProductCreateDTO productCreateDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Long id = this.restProductService.addProduct(productCreateDTO);
        return ResponseEntity.created(
                uriComponentsBuilder.path("/api/product/{id}").build(id)
        ).build();
    }


}
