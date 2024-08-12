package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.RestProductService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import java.util.List;

@Service
public class RestProductServiceImpl implements RestProductService {

    private final RestClient restClient;

    public RestProductServiceImpl(RestClient restClient) {
        this.restClient = restClient;
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
    public ProductDetailsDTO getProductById(Long id) {
        return restClient
                .get()
                .uri("http://localhost:8081/api/products/get/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ProductDetailsDTO.class);
    }

    @Override
    public void deleteProduct(Long id) {
        restClient
                .delete()
                .uri("http://localhost:8081/api/products/remove/{id}",id)
                .retrieve();
    }

    @Override
    public Long addProduct(ProductCreateDTO productCreateDTO) {

        try {
            RestClient.ResponseSpec retrieve = restClient
                    .post()
                    .uri("http://localhost:8081/api/products/create")
                    .body(productCreateDTO)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                String errorMessage = e.getResponseBodyAsString();
                throw new IllegalArgumentException(errorMessage);
            }
            throw new IllegalArgumentException("Failed to add product " + e.getMessage(), e);
        }

        return null;
    }
}
