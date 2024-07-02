package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.cartDTOS.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.DeliveryDataRepositories;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryDataServiceImplTest {
    @Autowired
    private DeliveryDataServiceImpl deliveryDataService;

    @Autowired
    private DeliveryDataRepositories deliveryDataRepositories;

    @Autowired
    private UserRepositories userRepositories;

    @BeforeEach
    void setUp(){
        deliveryDataRepositories.deleteAll();
        userRepositories.deleteAll();
    }
    @AfterEach
    void cleanUp(){
        deliveryDataRepositories.deleteAll();
        userRepositories.deleteAll();
    }
    @Test
    public void addAddress(){
        userRepositories.save(user());
        String email = user().getEmail();
        deliveryDataService.addAddress(deliveryDataDTO(),email);
        assertEquals(1,deliveryDataRepositories.count());
    }
    @Test
    public void addIncorrectData(){
        userRepositories.save(user());
        String email = user().getEmail();
        DeliveryDataDTO deliveryDataDTO = new DeliveryDataDTO();
        assertThrows(NullPointerException.class, () -> {
            deliveryDataService.addAddress(deliveryDataDTO,email);
        });
    }
    @Test
    public void addWithEmptyUser(){
        String email = user().getEmail();
        DeliveryDataDTO deliveryDataDTO = new DeliveryDataDTO();
       assertThrows(NullPointerException.class, () -> {
            deliveryDataService.addAddress(deliveryDataDTO,email);
        });
    }
    @Test
    public void addMoreAddressInSameUser() {
        userRepositories.save(user());
        String email = user().getEmail();
        deliveryDataService.addAddress(deliveryDataDTO(),email);
        deliveryDataService.addAddress(deliveryDataDTO(),email);
        deliveryDataService.addAddress(deliveryDataDTO(),email);
        assertEquals(3,deliveryDataRepositories.count());
    }



    private DeliveryDataDTO deliveryDataDTO (){
        DeliveryDataDTO deliveryDataDTO = new DeliveryDataDTO();
        deliveryDataDTO.setCity("Sofia");
        deliveryDataDTO.setPostCode("1000");
        deliveryDataDTO.setAddress("ul Prilep 69");
        deliveryDataDTO.setDeliveryAddress("OFFICE");
        deliveryDataDTO.setFirm("EKONT");
        deliveryDataDTO.add();
        return deliveryDataDTO;
    }


    private User user (){
        User user = new User();
        user.setEmail("ango_z@abv.bg");
        user.setFullName("Angel Zlatkov");
        user.setPhone("0893451813");
        user.setRole(UserRoleEnum.USER);
        user.setPassword("123456");
        return user;
    }


}