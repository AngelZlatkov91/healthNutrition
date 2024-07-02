package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BrandRepositoryTest {

    @Autowired
    BrandRepository brandRepository;

    private BrandProduct brandProduct;

    @BeforeEach
    public void setUp(){
        brandProduct = new BrandProduct();
        brandProduct.setBrand("Amix");
        brandProduct.setImageUrl("https://www.moremuscle.com/img/m/209.jpg");
        brandRepository.save(brandProduct);
    }

    @AfterEach
    public void tearDown(){
        brandRepository.delete(brandProduct);
    }

    @Test
    public void getBrand(){
        Optional<BrandProduct> byBrand = brandRepository.findByBrand("Amix");
        assertEquals(byBrand.get().getBrand(),"Amix");
    }

    @Test
    public void addExistingBrand(){
        BrandProduct brandProduct1 = new BrandProduct();
        brandProduct1.setBrand("Amix");
        brandProduct1.setImageUrl("Amix image url");
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            brandRepository.save(brandProduct1);

        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}