package healthnutrition.healthnutrition.web.UserController;

import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.*;
import healthnutrition.healthnutrition.services.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "angel@gmail.com",roles = {"USER"})
class DeliveryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartRepositories shoppingCartRepositories;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;
    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        typeRepository.deleteAll();
        brandRepository.deleteAll();
    }


    @Test
    public void testGetDeliveryForm() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(get("/delivery"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        boolean bindingResult = mvcResult.getModelAndView().getModel().containsKey("org.springframework.validation.BindingResult.data");
        boolean data = mvcResult.getModelAndView().getModel().containsKey("data");
        boolean address = mvcResult.getModelAndView().getModel().containsKey("address");
         boolean nameFirm = mvcResult.getModelAndView().getModel().containsKey("nameFirm");
         assertTrue(bindingResult);
         assertTrue(data);
         assertTrue(address);
         assertTrue(nameFirm);
    }

    @Test
    public void testFinalDelivery() throws Exception {
        addUser();
        Product product = createProduct2();
        shoppingCartService.addProductToShoppingCart(product.getName());
                mockMvc.perform(post("/delivery")
                                .param("city","SOFIA")
                                .param("postCode","1000")
                                .param("address","SOFIA")
                                .param("firm","SPEEDY")
                                .param("deliveryAddress","OFFICE"))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection());
        long count = shoppingCartRepositories.count();
        assertEquals(1,count);
    }

    @Test
    public void testFinalDeliveryData() throws Exception {

                mockMvc.perform(post("/delivery")
                                .param("city","")
                                .param("postCode","")
                                .param("address","")
                                .param("firm","")
                                .param("deliveryAddress",""))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection());
        long count = shoppingCartRepositories.count();
        assertEquals(1,count);
    }

    @Test
    public void testSuccessesDelivery() throws Exception {
        mockMvc.perform(get("/succses-delivery"))
                .andDo(print())
                .andExpect(status().isOk());
    }



    private Product createProduct2(){
        Product product = new Product();
        product.setName("FAT");
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
    private void addUser(){
        User user = new User();
        user.setFullName("Angel");
        user.setPhone("0123456789");
        user.setEmail("angel@gmail.com");
        user.setPassword("1234");
        user.setRole(UserRoleEnum.USER);
        userRepositories.save(user);
    }

}