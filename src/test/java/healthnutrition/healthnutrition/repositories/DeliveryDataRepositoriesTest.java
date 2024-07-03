package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Address;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DeliveryDataRepositoriesTest {

    @Autowired
    DeliveryDataRepositories deliveryDataRepositories;

    private Address address;

    @BeforeEach
    public void setUp(){
        address = new Address();
        address.setCity("Sofia");
        address.setPostCode("1000");
        address.setAddress("str. ivan ivanov");
        address.setFirm(DeliveryFirmEnum.EKONT);
        address.setPriceForDelivery(6.00);
        address.setDeliveryAddress(DeliveryAddress.OFFICE);
        deliveryDataRepositories.save(address);
    }

    @AfterEach
    public void tearDown() {
        deliveryDataRepositories.delete(address);
    }

    @Test
    public void correctSave(){
        assertEquals(1,deliveryDataRepositories.count());
    }

    @Test
    public void incorrectDataThrows(){
        Address address1 = new Address();
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            deliveryDataRepositories.save(address1);
        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }




}