package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TypeRepositoryTest {

    @Autowired
    TypeRepository typeRepository;
    private TypeProduct typeProduct;

    @BeforeEach
    public void setUp() {
        typeProduct = new TypeProduct();
        typeProduct.setType("Protein");
        typeRepository.save(typeProduct);
    }
    @AfterEach
    public void tearDown(){
        typeRepository.delete(typeProduct);
    }

    @Test
    public void getType(){
        Optional<TypeProduct> byType =
                typeRepository.findByType("Protein");
        assertEquals(byType.get().getType(),"Protein");
    }

    @Test
    public void addExistingType(){
        TypeProduct typeProduct1 = new TypeProduct();
        typeProduct1.setType("Protein");

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            typeRepository.save(typeProduct1);
        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}