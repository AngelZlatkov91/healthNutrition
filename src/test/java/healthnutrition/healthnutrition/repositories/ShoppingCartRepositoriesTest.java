package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Address;
import healthnutrition.healthnutrition.models.entitys.ProductInCart;
import healthnutrition.healthnutrition.models.entitys.ShoppingCart;
import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ShoppingCartRepositoriesTest {


    @Autowired
    ShoppingCartRepositories shoppingCartRepositories;
    @Autowired
    UserRepositories userRepositories;

    @Autowired
    ProductInCartRepositories productInCartRepositories;
    @Autowired
    DeliveryDataRepositories deliveryDataRepositories;

    private ShoppingCart shoppingCart;
    private List<ProductInCart> products;
    private Address address;
    private User user;
    private UUID uuid;


    @BeforeEach
    public void setUp(){
        user = new User();
        extractedUser();
        userRepositories.save(user);
        products = new ArrayList<>();
        extractedProduct();
        productInCartRepositories.saveAll(products);
        address = new Address();
        extractAddress();
        deliveryDataRepositories.save(address);
        shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setGivenToDeliveriFirm(true);
        shoppingCart.setDeliveryNumber(UUID.randomUUID());
        shoppingCart.setDate(LocalDate.now());
        shoppingCart.setPrice(150.00);
        shoppingCart.setProducts(products);
        uuid = shoppingCart.getDeliveryNumber();
        shoppingCartRepositories.save(shoppingCart);
    }



    @AfterEach
    public void tearDown(){
        shoppingCartRepositories.delete(shoppingCart);
        productInCartRepositories.deleteAll(products);
        userRepositories.delete(user);
    }

    @Test
    public void testGetShoppingCartWithUser(){
        List<ShoppingCart> allByUserOrderByDateDesc = shoppingCartRepositories.findAllByUserOrderByDateDesc(user);
        assertEquals(1,allByUserOrderByDateDesc.size());
        ShoppingCart shoppingCart1 = allByUserOrderByDateDesc.get(0);
        assertEquals(uuid,shoppingCart1.getDeliveryNumber());
    }
    @Test
    public void testAddEmptyShoppingCart(){
        ShoppingCart shoppingCart1 = new ShoppingCart();
        assertThrows(DataIntegrityViolationException.class, () -> {
            shoppingCartRepositories.save(shoppingCart1);
        });
    }


    private void extractedProduct() {
       ProductInCart product = new ProductInCart();
        product.setName("Isolate");
        product.setQuantity(1);
        product.setSinglePrice(50.00);
        ProductInCart  product1 = new ProductInCart();
        product1.setName("tribulos");
        product1.setQuantity(2);
        product1.setSinglePrice(50.00);
        products.add(product);
        products.add(product1);
    }


    private void extractedUser() {
        user.setFullName("Angel Ivanov");
        user.setPhone("0893451814");
        user.setEmail("angoz@abv.bg");
        user.setPassword("1234");
        user.setRole(UserRoleEnum.USER);
    }
    private void extractAddress() {
      address.setCity("Sofia");
      address.setPostCode("1000");
      address.setAddress("str. Prilep 69");
      address.setFirm(DeliveryFirmEnum.EKONT);
      address.setDeliveryAddress(DeliveryAddress.ADDRESS);
      address.setPriceForDelivery(8.00);
    }
}