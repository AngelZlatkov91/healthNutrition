package healthnutrition.healthnutrition.repositories;
import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoriesTest {

    @Autowired
     UserRepositories userRepositories;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setFullName("Angel Ivanov");
        user.setPhone("0893451814");
        user.setEmail("angoz@abv.bg");
        user.setPassword("1234");
        user.setRole(UserRoleEnum.USER);
        userRepositories.save(user);
    }

    @AfterEach
    public void tearDown(){
        userRepositories.delete(user);
    }


    @Test
    public void UserFindByUserName(){
        String email = "angoz@abv.bg";
        Optional<User> byEmail = userRepositories.findByEmail(email);
        Assertions.assertEquals(byEmail.get().getEmail(),email);
    }
    @Test
    public void UserFindByPhone(){
        String phone = "0893451814";
        Optional<User> byPhone = userRepositories.findByPhone(phone);
        Assertions.assertEquals(byPhone.get().getPhone(),phone);
    }

    @Test
    public void AddSameUser() {
        userRepositories.save(user);
        long count = userRepositories.count();
        assertEquals(count, 1);
    }

    @Test
    public void editUser(){
        //edit phone
        String newPhone = "0123456789";
        user.setPhone(newPhone);
        User save = userRepositories.save(user);
        Assertions.assertEquals(save.getPhone(),newPhone);

    }

    @Test
    public void throwUserWithExistEmail(){
        User user1 = difrenUser();
        user1.setEmail("angoz@abv.bg");
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            userRepositories.save(user1);
        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }




    private User difrenUser(){
        User user1 = new User();
        user1.setFullName("Angel");
        user1.setPhone("0123456789");
        user1.setEmail("ango@abv.bg");
        user1.setPassword("1234");
        user1.setRole(UserRoleEnum.USER);
        return user1;
    }

}