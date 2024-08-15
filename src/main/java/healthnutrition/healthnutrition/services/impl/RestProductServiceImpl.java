package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductEditPrice;
import healthnutrition.healthnutrition.services.RestProductService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ProductDetailsDTO getProductByName(String name) {
        System.out.println();
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

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                String errorMessage = e.getResponseBodyAsString();
                throw new IllegalArgumentException(errorMessage);
            }
            throw new IllegalArgumentException("Failed to add product " + e.getMessage(), e);
        }
    }

    @Override
    public void editPrice(ProductEditPrice productEditPrice) {
        try {
            restClient
                    .put()
                    .uri("http://localhost:8081/api/products/edit/price/{name}", productEditPrice.getName())
                    .body(productEditPrice)
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

    @Override
    public List<ProductDetailsDTO> getAllProducts(String searchKey) {
        return restClient
                .get()
                .uri("http://localhost:8081/api/products/search/{searchKey}",searchKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductDetailsDTO>>() {
                });
    }

    @Override
    public ProductInCartDTO findProduct(String name) {
        return restClient
                .get()
                .uri("http://localhost:8081/api/products/cart/{name}",name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ProductInCartDTO.class);
    }



}
