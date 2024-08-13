package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.Exception.DatabaseException;
import healthnutrition.healthnutrition.models.dto.userDTOS.EditUserDTO;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRegisterDTo;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserUpdateDTO;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    // this integration test work only when make all test work for look coverage in the all app
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepositories userRepositoriesTest;

//    @BeforeEach
//    void setUp(){
//        userRepositoriesTest.deleteAll();
//    }
//    @AfterEach
//    void cleanUp(){
//        userRepositoriesTest.deleteAll();
//    }
    @Test
    public void testRegisterUser(){
        UserRegisterDTo userRegisterDTo = userDTO();

        assertEquals(4, userRepositoriesTest.count());
    }
    @Test
    public void testUserRegisterWithIncorrectData(){
        UserRegisterDTo userRegisterDTo = new UserRegisterDTo();

        Exception exception = assertThrows(DatabaseException.class, () -> {
            userService.registerUser(userRegisterDTo);
        });
//        String expectedMessage = "Cannot invoke";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void UserRegisterWithExistingData(){

         assertThrows(DatabaseException.class, () -> {
            userService.registerUser(userDTO());
        });


    }

    @Test
    public void getUserData(){

        assertThrows(DatabaseException.class, () -> {
            userService.registerUser(userDTO());
        });

    }
    @Test
    public void editUserData(){
        UserRegisterDTo userRegisterDTo = userDTO();
        userService.registerUser(userRegisterDTo);
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setFullName("");
        editUserDTO.setPhone("");
        editUserDTO.setEmail("ango_z@abv.bg");
        userService.edit(editUserDTO,userRegisterDTo.getEmail());

        UserUpdateDTO userData = userService.getUserData("ango_z@abv.bg");
        assertEquals(userData.getEmail(),editUserDTO.getEmail());
    }
    @Test
    public void editUserDataAll(){
        UserRegisterDTo userRegisterDTo = userDTO();
        userService.registerUser(userRegisterDTo);
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setFullName("Ivaylo");
        editUserDTO.setPhone("0878343740");
        editUserDTO.setEmail("edit@abv.bg");
        userService.edit(editUserDTO,userRegisterDTo.getEmail());

        UserUpdateDTO userData = userService.getUserData("edit@abv.bg");
        assertEquals(userData.getEmail(),editUserDTO.getEmail());
        assertEquals(userData.getFullName(),editUserDTO.getFullName());

    }




    private UserRegisterDTo userDTO(){
        UserRegisterDTo userRegisterDTo = new UserRegisterDTo();
        userRegisterDTo.setFullName("Angel zlatkov");
        userRegisterDTo.setEmail("test@abv.bg");
        userRegisterDTo.setPhone("0898383817");
        userRegisterDTo.setPassword("1324");
        userRegisterDTo.setConfirmPassword("1324");
        return userRegisterDTo;
    }

}