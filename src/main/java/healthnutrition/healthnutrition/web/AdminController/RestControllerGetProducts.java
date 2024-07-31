package healthnutrition.healthnutrition.web.AdminController;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.RestProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
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
    @GetMapping(value = "/get/product/{uuid}")
    public ResponseEntity<ProductDetailsDTO> getByUUID(@PathVariable("uuid") UUID uuid) {
       Optional<ProductDetailsDTO> productDetails = restProductService.getProductById(uuid);
        return productDetails.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // remove product by uuid permit only admin

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<ProductDetailsDTO> deleteById(@PathVariable("uuid") UUID uuid) {
        restProductService.deleteProduct(uuid);
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
