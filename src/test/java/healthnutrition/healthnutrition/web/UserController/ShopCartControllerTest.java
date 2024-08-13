package healthnutrition.healthnutrition.web.UserController;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ShoppingCartDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import healthnutrition.healthnutrition.services.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user",roles = {"USER"})
class ShopCartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        typeRepository.deleteAll();
        brandRepository.deleteAll();
    }

    @Test
    public  void testGetViewToShoppingCart() throws Exception {
        Product product = createProduct();
        shoppingCartService.addProductToShoppingCart(product.getName());
        MvcResult mvcResult =
                mockMvc.perform(get("/shopping_cart"))
                .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        Double price = (Double) mvcResult.getModelAndView().getModel().get("price");
       List<ProductInCartDTO> products = (List<ProductInCartDTO>) mvcResult.getModelAndView().getModel().get("products");
        ProductInCartDTO productInCartDTO = products.get(0);
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.productInCart();
         assertEquals(1,shoppingCartDTO.getProducts().size());
        assertEquals(1,products.size());
        assertEquals(50.00,price);
        assertEquals("ISOLATE",productInCartDTO.getName());

    }

    @Test
    public void testRemoveProductFromShoppingCart() throws Exception {
        Product product = createProduct();
        shoppingCartService.addProductToShoppingCart(product.getName());
                mockMvc.perform(get("/remove/product/{getName}","ISOLATE" ))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection());
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.productInCart();
        boolean empty = shoppingCartDTO.getProducts().isEmpty();
        assertTrue(empty);
    }

    @Test
    public void testDecreaseProductQuantityFromShoppingCart() throws Exception {
        Product product = createProduct();
        shoppingCartService.addProductToShoppingCart(product.getName());
        shoppingCartService.addProductToShoppingCart(product.getName());

                mockMvc.perform(get("/decrease/product/{getName}","ISOLATE" ))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection());
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.productInCart();
        ProductInCartDTO productInCartDTO = shoppingCartDTO.getProducts().get("ISOLATE");
        assertEquals(1,productInCartDTO.getQuantity());

    }
    @Test
    public void testIncreaseProductQuantityFromShoppingCart() throws Exception {
        Product product = createProduct2();
        shoppingCartService.addProductToShoppingCart(product.getName());
                mockMvc.perform(get("/increase/product/{getName}","FAT" ))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection());
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.productInCart();
        ProductInCartDTO productInCartDTO = shoppingCartDTO.getProducts().get("FAT");
        assertEquals(2,productInCartDTO.getQuantity());

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
}