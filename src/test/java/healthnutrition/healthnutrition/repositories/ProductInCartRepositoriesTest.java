package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.ProductInCart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductInCartRepositoriesTest {

    @Autowired
    ProductInCartRepositories productInCartRepositories;

    private ProductInCart product;
    private ProductInCart product1;

    @BeforeEach
    public void setUp(){
        product = new ProductInCart();
        product.setName("Isolate");
        product.setQuantity(1);
        product.setSinglePrice(50.00);
        product1 = new ProductInCart();
        product1.setName("tribulos");
        product1.setQuantity(2);
        product1.setSinglePrice(50.00);
        productInCartRepositories.save(product);
        productInCartRepositories.save(product1);
    }

    @AfterEach
    public void tearDown(){
        productInCartRepositories.delete(product);
        productInCartRepositories.delete(product1);
    }

    @Test
    public void testIsCorrectSave(){
        assertEquals(2,productInCartRepositories.count());
    }


    @Test
    public void addNullProducts(){
        ProductInCart productInCart = new ProductInCart();
        assertThrows(DataIntegrityViolationException.class, () -> {
           productInCartRepositories.save(productInCart);
        });
    }


}