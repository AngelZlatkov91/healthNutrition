package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.cartDTOS.*;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.entitys.*;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartServiceImplTest {
    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    private ShoppingCartRepositories shoppingCartRepositories;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private ProductInCartRepositories productInCartRepositories;
    @Autowired
    private DeliveryDataRepositories deliveryDataRepositories;



    @BeforeEach
    void setUp(){
        productInCartRepositories.deleteAll();
        shoppingCartRepositories.deleteAll();
        userRepositories.deleteAll();
        deliveryDataRepositories.deleteAll();

        saveData();
    }
    @AfterEach
    void cleanUp(){
        shoppingCartRepositories.deleteAll();
        productInCartRepositories.deleteAll();
        userRepositories.deleteAll();
        deliveryDataRepositories.deleteAll();

    }

    @Test
    public void testAddProductToCart(){
        shoppingCartService.productInCart().getProducts().put("Protein",createProduct());
        assertEquals(1,shoppingCartService.productInCart().getProducts().size());

    }
    @Test
    public void testAddSameProductAndIncreaseQuantity(){
        shoppingCartService.productInCart().getProducts().put("Protein",createProduct());
        ProductInCartDTO productInCartDTO = shoppingCartService.productInCart().getProducts().get("Protein");
        productInCartDTO.increaseQuantity();
        Map<String, ProductInCartDTO> products = shoppingCartService.productInCart().getProducts();
        ProductInCartDTO protein = products.get("Protein");
        assertEquals(2,protein.getQuantity());

    }
    @Test
    public void testIncreaseQuantity(){
        shoppingCartService.productInCart().getProducts().put("Protein",createProduct());
        shoppingCartService.increase("Protein");
        Map<String, ProductInCartDTO> products = shoppingCartService.productInCart().getProducts();
        ProductInCartDTO fatBurner = products.get("Protein");
        assertEquals(2,fatBurner.getQuantity());
        assertEquals(100.00,shoppingCartService.calculateTotalPrice());

    }
    @Test
    public void testDecreaseQuantity(){
        shoppingCartService.productInCart().getProducts().put("Protein",createProduct());
        shoppingCartService.increase("Protein");
        shoppingCartService.decrease("Protein");
        Map<String, ProductInCartDTO> products = shoppingCartService.productInCart().getProducts();
        ProductInCartDTO vitamin = products.get("Protein");
        assertEquals(1,vitamin.getQuantity());

    }

    @Test
    public void testRemoveProductFromCart(){
        shoppingCartService.productInCart().getProducts().put("Protein",createProduct());
        shoppingCartService.remove("Protein");
        int size = shoppingCartService.productInCart().getProducts().size();
        assertEquals(0,size);

    }




   private void saveData(){
        userRepositories.save(user());
   }

    private ProductInCartDTO createProduct(){
        ProductInCartDTO product = new ProductInCartDTO();
                product.setName("Protein");
                product.setPrice(50.00);
               product.increaseQuantity();
        return product;
    }

    private ProductInCartDTO createProduct2(){
        ProductInCartDTO product = new ProductInCartDTO();
        product.setName("Tribulus");
        product.setPrice(50.00);
        product.increaseQuantity();
        return product;
    }

    private User user (){
        User user = new User();
        user.setFullName("Angel");
        user.setEmail("ango_z@abv.bg");
        user.setPhone("0893451813");
        user.setRole(UserRoleEnum.USER);
        user.setPassword("123456");
        return user;
    }


    private DeliveryDataDTO address(){
        DeliveryDataDTO address = new DeliveryDataDTO();
            address.setCity("Sofia");
            address.setPostCode("1000");
            address.setAddress("str. Prilep 69");
            address.setFirm("EKONT");
            address.setDeliveryAddress("ADDRESS");
            address.add();
        return address;
    }






}