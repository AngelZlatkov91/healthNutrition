package healthnutrition.healthnutrition.web.UserController;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProductRepository productRepository;


    @AfterEach
    public  void tearDown(){
        productRepository.deleteAll();
        typeRepository.deleteAll();
        brandRepository.deleteAll();
    }

    @Test
    public void testGetAllProductsPage() throws Exception {
        Product product = createProduct();
        MvcResult mvcResult = this.mockMvc.perform(get("/products/all")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        boolean products = mvcResult.getModelAndView().getModel().containsKey("products");
        List<ProductDetailsDTO> products1 = (List<ProductDetailsDTO>) mvcResult.getModelAndView().getModel().get("products");
        assertEquals(2,products1.size());
        assertTrue(products);
    }

    @Test
    public  void testGetProductByNamePage() throws Exception {
        Product product = createProduct();
        MvcResult mvcResult = this.mockMvc.perform(get("/product/{name}",product.getName())).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        boolean products = mvcResult.getModelAndView().getModel().containsKey("product");
        ProductDetailsDTO products1 = (ProductDetailsDTO) mvcResult.getModelAndView().getModel().get("product");
        assertNull(products1);
    }

    @Test
    public void testAddProductToCartByName() throws Exception {
        Product product = createProduct();
        MvcResult mvcResult = this.mockMvc.perform(get("/product/add-shoppingCart/{name}", product.getName()).with(csrf())).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        String pathInfo = mvcResult.getRequest().getPathInfo();
        String currentPath = "/product/add-shoppingCart/" + product.getName();
        assertEquals(pathInfo,currentPath);
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