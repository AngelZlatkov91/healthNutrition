package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.services.RestProductService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.UUID;

@Service
public class RestProductServiceImpl implements RestProductService {

    private final RestClient restClient;
    private final ProductRepository productRepository;

    public RestProductServiceImpl(RestClient restClient, ProductRepository productRepository) {
        this.restClient = restClient;
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductDetailsDTO> getAllProducts() {
       return restClient
                .get()
                .uri("http://localhost:8081/api/products")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductDetailsDTO>>() {
                });
    }

    @Override
    public ProductDetailsDTO getProductById(String name) {
        return restClient
                .get()
                .uri("http://localhost:8081/api/products/get/{name}",name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ProductDetailsDTO.class);
    }

    @Override
    public void deleteProduct(String name) {
        restClient
                .delete()
                .uri("http://localhost:8081/api/products/remove/{name}",name)
                .retrieve();
    }

    @Override
    public void addProduct(ProductCreateDTO productCreateDTO) {

        try {
            RestClient.ResponseSpec retrieve = restClient
                    .post()
                    .uri("http://localhost:8081/api/products/create")
                    .body(productCreateDTO)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve();
            System.out.println();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                String errorMessage = e.getResponseBodyAsString();
                throw new IllegalArgumentException(errorMessage);
            }
            throw new IllegalArgumentException("Failed to add product " + e.getMessage(), e);
        }
    }
}
