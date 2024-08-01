package healthnutrition.healthnutrition.web.AdminController;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin",roles = {"ADMIN"})
class ProductControllerTest {
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
    public void testOpenAddProduct() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/product-add")
                        .with(csrf())
                ).andDo(print())
                .andExpect(status().isOk()).andReturn();
        boolean modelProduct = mvcResult.getModelAndView().getModel().containsKey("productCreateDTO");
        boolean modelBrands = mvcResult.getModelAndView().getModel().containsKey("brands");
        boolean modelTypes = mvcResult.getModelAndView().getModel().containsKey("types");
      assertTrue(modelProduct);
      assertTrue(modelBrands);
      assertTrue(modelTypes);
    }

    @Test
    public void testAddCorrectProduct() throws Exception {
        BrandProduct brandProduct = addBrand();
        TypeProduct typeProduct = addType();
        MvcResult mvcResult = mockMvc.perform(post("/product-add")
                .param("name", "ISOLATE")
                .param("description", "description for product")
                .param("price", "50.00")
                .param("imageUrl", "picture for the current product")
                .param("type", typeProduct.getType())
                .param("brand", brandProduct.getBrand())
                .with(csrf())
        ).andDo(print()).andExpect(status().is3xxRedirection()).andReturn();
        Optional<Product> byName = productRepository.findByName("ISOLATE");
        assertEquals(byName.get().getDescription(),"description for product");
        assertEquals(1,productRepository.count());
    }

    @Test
    public void testAddNotCorrectProduct() throws Exception {
        BrandProduct brandProduct = addBrand();
        TypeProduct typeProduct = addType();
        MvcResult mvcResult = mockMvc.perform(post("/product-add")
                .param("name", "")
                .param("description", "description for product")
                .param("price", "")
                .param("imageUrl", "picture for the current product")
                .param("type", typeProduct.getType())
                .param("brand", brandProduct.getBrand())
                .with(csrf())
        ).andDo(print()).andExpect(status().is3xxRedirection()).andReturn();
        assertEquals(0,productRepository.count());
    }

    @Test
    public void testDeleteProductByUUID() throws Exception {
        Product product = createProduct();
        MvcResult mvcResult = mockMvc.perform(delete("/product/remove/{uuid}", product.getUuid()))
                .andDo(print())
                .andExpect(status().is3xxRedirection()).andReturn();
       assertEquals(0,productRepository.count());
    }

    private Product createProduct(){
        Product product = new Product();
        product.setName("GAINER");
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