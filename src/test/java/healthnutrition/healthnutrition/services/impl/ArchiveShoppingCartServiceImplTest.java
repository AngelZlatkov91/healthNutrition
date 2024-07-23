package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.cartDTOS.ArchiveDTO;
import healthnutrition.healthnutrition.models.entitys.*;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArchiveShoppingCartServiceImplTest {

    @Autowired
    private ArchiveShoppingCartServiceImpl archiveShoppingCartService;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private ShoppingCartRepositories shoppingCartRepositories;
    @Autowired
    private ProductInCartRepositories productInCartRepositories;
    @Autowired
    private DeliveryDataRepositories deliveryDataRepositories;

    @BeforeEach
    void setUp(){
        productInCartRepositories.deleteAll();
        deliveryDataRepositories.deleteAll();
        shoppingCartRepositories.deleteAll();
        userRepositories.deleteAll();


    }
    @AfterEach
    void cleanUp(){
        productInCartRepositories.deleteAll();
        shoppingCartRepositories.deleteAll();
        deliveryDataRepositories.deleteAll();
        userRepositories.deleteAll();

    }


    @Test
    public void testArchiveCartFromCurrentUser(){
      //  saveForm();
        // TODO
        ArchiveDTO archiveDTO = archiveShoppingCartService.allShoppingCarts(user().getEmail());
        int size = archiveDTO.getArchiveShoppingCartDTOS().size();
        assertEquals(1,size);
    }

    private void saveForm()  {
        ShoppingCart shoppingCart = shoppingCart();
        shoppingCart.setAddress(address());
        shoppingCart.setProducts(List.of(product()));
        shoppingCart.setUser(user());
        User user = user();
        userRepositories.save(user);
        user.setShoppingCarts(Set.of(shoppingCart));
        deliveryDataRepositories.save(address());
        productInCartRepositories.save(product());
        shoppingCartRepositories.save(shoppingCart);


    }


    private User user (){
        User user = new User();
        user.setFullName("Angel");
        user.setEmail("Angel@abv.bg");
        user.setPhone("0893451813");
        user.setRole(UserRoleEnum.USER);
        user.setPassword("1234");
        return user;
    }

    private ProductInCart product (){
        ProductInCart product = new ProductInCart();
        product.setName("TEST");
        product.setQuantity(1);
        product.setPrice(50.00);
        return product;
    }
    private ShoppingCart shoppingCart (){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDeliveryNumber(UUID.randomUUID());
        shoppingCart.setDate(LocalDate.now());
        shoppingCart.setPrice(50.00);
        shoppingCart.setGivenToDeliveriFirm(true);
        return shoppingCart;
    }

    private Address address(){
        Address address = new Address();
        address.setAddress("test street");
        address.setCity("sofia");
        address.setPostCode("2850");
        address.setDeliveryAddress(DeliveryAddress.ADDRESS);
        address.setFirm(DeliveryFirmEnum.EKONT);
        address.setPriceForDelivery(10.00);
        return address;
    }



}