package healthnutrition.healthnutrition.web.UserController;
import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepositories userRepositories;

    @AfterEach
    public void tearDown(){
        userRepositories.deleteAll();
    }



    @Test
   public void testOpenRegisterForm() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/users/register")
                ).andExpect(status().isOk()).andDo(print())
                .andReturn();
        boolean userDTO = mvcResult.getModelAndView().getModel().containsKey("userRegistrationDTO");
        boolean bindingResult = mvcResult.getModelAndView().getModel().containsKey("org.springframework.validation.BindingResult.userRegistrationDTO");
        assertTrue(userDTO);
        assertTrue(bindingResult);

    }
    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("fullName","Kiril ivanov")
                        .param("email", "test@test.com")
                        .param("phone", "0893451813")
                        .param("password", "test")
                        .param("confirmPassword", "test")
        ).andExpect(status().is3xxRedirection());
        assertEquals(1,userRepositories.count());
    }

    @Test
    void testRegistrationNotCorrectData() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("fullName","")
                        .param("email", "")
                        .param("phone","")
                        .param("password", "test")
                        .param("confirmPassword", "test5")
        ).andExpect(status().is3xxRedirection());
        assertEquals(0,userRepositories.count());
    }
    @Test
    void testRegistrationWithExistEmailData() throws Exception {
        addUser();
        mockMvc.perform(post("/users/register")
                .param("fullName","test1")
                .param("email", "angel@gmail.com")
                .param("phone","9876543210")
                .param("password", "test")
                .param("confirmPassword", "test")
        ).andExpect(status().is3xxRedirection());
        assertEquals(1,userRepositories.count());
    }
    @Test
    void testRegistrationWithExistPhoneData() throws Exception {
        addUser();
        mockMvc.perform(post("/users/register")
                .param("fullName","test2")
                .param("email", "test@gmail.com")
                .param("phone","0123456789")
                .param("password", "test")
                .param("confirmPassword", "test")
        ).andExpect(status().is3xxRedirection());
        assertEquals(1,userRepositories.count());
    }
    @Test
    void testRegistrationNotCorrectDataPasswordNodMatch() throws Exception {
        addUser();
        mockMvc.perform(post("/users/register")
                .param("fullName","test3")
                .param("email", "test3@test.com")
                .param("phone","9876543210")
                .param("password", "test")
                .param("confirmPassword", "test5")
        ).andExpect(status().is3xxRedirection());
        assertEquals(1,userRepositories.count());
    }


    private void addUser(){
        User user = new User();
        user.setFullName("Angel");
        user.setPhone("0123456789");
        user.setEmail("angel@gmail.com");
        user.setPassword("1234");
        user.setRole(UserRoleEnum.USER);
         userRepositories.save(user);
    }
}