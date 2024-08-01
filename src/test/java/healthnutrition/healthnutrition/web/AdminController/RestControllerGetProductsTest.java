package healthnutrition.healthnutrition.web.AdminController;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class RestControllerGetProductsTest {
     @Autowired
    private ProductRepository productRepository;
     @Autowired
     private BrandRepository brandRepository;
     @Autowired
     private TypeRepository typeRepository;
     @Autowired
    private MockMvc mockMvc;

     @AfterEach
             public void tearDown(){
         productRepository.deleteAll();
         brandRepository.deleteAll();
         typeRepository.deleteAll();
     }




        @Test
        public void testGetByUUID() throws Exception {

            // Arrange
            var actualEntity = createProduct();

            // ACT
            ResultActions result = mockMvc
                    .perform(get("/api/products/get/product/{uuid}", actualEntity.getUuid())
                            .contentType(MediaType.APPLICATION_JSON));

            // Assert
            result.andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(actualEntity.getName())))
                    .andExpect(jsonPath("$.description", is(actualEntity.getDescription())))
                    .andExpect(jsonPath("$.brant", is(actualEntity.getBrant().getBrand())))
                    .andExpect(jsonPath("$.price", is(actualEntity.getPrice())));
        }

        @Test
        public void testProductNotFound() throws Exception {
            mockMvc
                    .perform(get("/api/products/get/product/{uuid}", "1000000")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError());
        }

        @Test
        public void testCreateProduct() throws Exception {
            // Arrange
            var actualEntity = createProduct();

            MvcResult result = mockMvc.perform(post("/api/products/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                 {
                    "name" : "MACA6",
                    "description" : "Test description",
                    "price" : 50.00,
                    "imageUrl" : "https://nowfoods.bg/image/catalog/4721_mainimage_1.jpg" ,
                    "type" : "PROTEIN",
                    "brand" : "AMIX"
                 }
                """)
                    ).andExpect(status().isCreated())
                    .andExpect(header().exists("Location"))
                    .andReturn();
            Optional<Product> byUuid = productRepository.findByName("MACA6");

            Assertions.assertEquals("Test description", byUuid.get().getDescription());
            Assertions.assertEquals(50.00, byUuid.get().getPrice());
            Assertions.assertEquals("MACA6", byUuid.get().getName());

        }

        @Test
        public void testDeleteOffer() throws Exception {
            var actualEntity = createProduct();

            mockMvc.perform(delete("/api/products/remove/{uuid}", actualEntity.getUuid())
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNoContent());
            Assertions.assertEquals(0,productRepository.count());
        }

    private Product createProduct(){
         BrandProduct brand = new BrandProduct();
         brand.setBrand("AMIX");
         brand.setImageUrl("test image");
         brandRepository.save(brand);
        TypeProduct type = new TypeProduct();
        type.setType("PROTEIN");
        typeRepository.save(type);

        Product product = new Product();
        product.setUuid(UUID.randomUUID());
        product.setBrant(brand);
        product.setType(type);
        product.setPrice(50.00);
        product.setDescription("test product");
        product.setImageUrl("test image");
        product.setName("ISOLATE");
        productRepository.save(product);
     return product;
     }

}