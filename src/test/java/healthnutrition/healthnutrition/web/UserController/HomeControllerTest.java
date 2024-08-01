package healthnutrition.healthnutrition.web.UserController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MockMvc mockMvc;


    @AfterEach
    public  void tearDown(){
        productRepository.deleteAll();
        typeRepository.deleteAll();
        brandRepository.deleteAll();
    }

    @Test
    public void givenHomePageURI() throws Exception {
        Product product = createProduct();
        MvcResult mvcResult = this.mockMvc.perform(get("/home")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        boolean brands = mvcResult.getModelAndView().getModel().containsKey("brands");
        boolean types = mvcResult.getModelAndView().getModel().containsKey("types");
        assertTrue(brands);
        assertTrue(types);
    }

    @Test
    public void testSearchKeyByWordExist() throws Exception {
        Product product = createProduct();
        MvcResult mvcResult = this.mockMvc.perform(get("/search/{searchKey}","isolate").param("searchKey","isolate"))
                .andExpect(status().isOk())
                .andReturn();
        boolean products = mvcResult.getModelAndView().getModel().containsKey("products");
        Object products1 = mvcResult.getModelAndView().getModel().get("products");
        String name = products1.toString();
        assertTrue(products);
        assertTrue(name.contains("ProductDetailsDTO"));
    }

    @Test
    public void testSearchKeyByWordNotExist() throws Exception {

        Product product = createProduct();
        MvcResult mvcResult = this.mockMvc.perform(get("/search/{searchKey}","fat burner").param("searchKey","fat burner"))
                .andExpect(status().isOk())
                .andReturn();
        boolean products = mvcResult.getModelAndView().getModel().containsKey("products");
        List<ProductDetailsDTO> products1 = (List<ProductDetailsDTO>) mvcResult.getModelAndView().getModel().get("products");
        assertEquals(0,products1.size());
        assertTrue(products);
    }

    @Test
    public void testSearchByBrand() throws Exception {

        Product product = createProduct();
        MvcResult mvcResult = this.mockMvc.perform(get("/brand/{searchKey}","AMIX")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        boolean products = mvcResult.getModelAndView().getModel().containsKey("products");
        List<ProductDetailsDTO> products1 = (List<ProductDetailsDTO>) mvcResult.getModelAndView().getModel().get("products");
        ProductDetailsDTO productDetailsDTO = products1.get(0);
        assertEquals(product.getBrant().getBrand(),productDetailsDTO.getBrant());
        assertTrue(products);
    }
    @Test
    public void testSearchByType() throws Exception {
        Product product = createProduct();
        MvcResult mvcResult = this.mockMvc.perform(get("/type/{searchKey}","PROTEIN")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        boolean products = mvcResult.getModelAndView().getModel().containsKey("products");
        List<ProductDetailsDTO> products1 = (List<ProductDetailsDTO>) mvcResult.getModelAndView().getModel().get("products");
        ProductDetailsDTO productDetailsDTO = products1.get(0);
        assertEquals(product.getBrant().getBrand(),productDetailsDTO.getBrant());
        assertTrue(products);
    }

    private Product createProduct(){
        Product product = new Product();
        product.setName("ISOLATE");
        product.setDescription("Test description");
        product.setPrice(50.00);
        product.setUuid(UUID.randomUUID());
        product.setBrant(addBrand());
        product.setType(addType());
        product.setImageUrl("https://www.moremuscle.com/img/m/209.jpg");
        return productRepository.save(product);
    }


    private BrandProduct addBrand() {
    BrandProduct brand = new BrandProduct();
    brand.setBrand("AMIX");
    brand.setImageUrl("https://www.moremuscle.com/img/m/209.jpg");
    return brandRepository.save(brand);
    }
    private TypeProduct addType(){
        TypeProduct type = new TypeProduct();
        type.setType("PROTEIN");
     return  typeRepository.save(type);
    }


}