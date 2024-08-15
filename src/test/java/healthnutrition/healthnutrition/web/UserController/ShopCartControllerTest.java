package healthnutrition.healthnutrition.web.UserController;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ShoppingCartDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import healthnutrition.healthnutrition.services.impl.RestProductServiceImpl;
import healthnutrition.healthnutrition.services.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private ShoppingCartServiceImpl shoppingCartService;



    @Test
    public  void testGetViewToShoppingCart() throws Exception {
        ProductInCartDTO product = createProduct();
        shoppingCartService.productInCart().getProducts().put("TEST",product);
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
        assertEquals("TEST",productInCartDTO.getName());

    }

    @Test
    public void testRemoveProductFromShoppingCart() throws Exception {
        ProductInCartDTO product = createProduct();
        shoppingCartService.productInCart().getProducts().put("TEST",product);
                mockMvc.perform(get("/remove/product/{getName}","TEST" ))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection());
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.productInCart();
        boolean empty = shoppingCartDTO.getProducts().isEmpty();
        assertTrue(empty);
    }

    @Test
    public void testDecreaseProductQuantityFromShoppingCart() throws Exception {
        ProductInCartDTO product = createProduct();
        shoppingCartService.productInCart().getProducts().put("TEST",product);
        ProductInCartDTO productInCartDTO1 = shoppingCartService.productInCart().getProducts().get("TEST");
        productInCartDTO1.increaseQuantity();

        mockMvc.perform(get("/decrease/product/{getName}","TEST" ))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection());
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.productInCart();
        ProductInCartDTO productInCartDTO = shoppingCartDTO.getProducts().get("TEST");
        assertEquals(1,productInCartDTO.getQuantity());

    }
    @Test
    public void testIncreaseProductQuantityFromShoppingCart() throws Exception {
        ProductInCartDTO product = createProduct();
        shoppingCartService.productInCart().getProducts().put("TEST",product);
                mockMvc.perform(get("/increase/product/{getName}","TEST" ))
                        .andDo(print())
                        .andExpect(status().is3xxRedirection());
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.productInCart();
        ProductInCartDTO productInCartDTO = shoppingCartDTO.getProducts().get("TEST");
        assertEquals(2,productInCartDTO.getQuantity());

    }
    private ProductInCartDTO createProduct(){
        ProductInCartDTO product = new ProductInCartDTO();
        product.setName("TEST");
        product.setPrice(50.00);
        product.increaseQuantity();
        return product;
    }

}