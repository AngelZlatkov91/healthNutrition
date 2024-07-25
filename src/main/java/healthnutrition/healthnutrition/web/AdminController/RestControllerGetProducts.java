package healthnutrition.healthnutrition.web.AdminController;

import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;

import healthnutrition.healthnutrition.services.ProductService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api")
public class RestControllerGetProducts {
    // very simple rest controller  to do more specific
           // TODO
    private final ProductService productService;

    public RestControllerGetProducts(ProductService productService) {
        this.productService = productService;
    }

    // get all products permit all
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDetailsDTO>> getAllOffers() {

        return ResponseEntity.ok(
                productService.getAllProducts("")
        );
    }
    // get product by uuid permit all
    @GetMapping(value = "/get/product/{uuid}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDetailsDTO> getById(@PathVariable("uuid") UUID uuid) {

        return ResponseEntity
                .ok(productService.getProductDetails(uuid));
    }
    // remove product by uuid permit only admin

    @DeleteMapping("/remove/product/{uuid}")
    public ResponseEntity<ProductDetailsDTO> deleteById(@PathVariable("uuid") UUID uuid) {
        productService.deleteProduct(uuid);
        return ResponseEntity
                .noContent()
                .build();
    }
    // create product  permit only admin
    @PostMapping("/create/product")
    public ResponseEntity<ProductCreateDTO> createOffer(
            @RequestBody ProductCreateDTO productCreateDTO
    ) {
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
