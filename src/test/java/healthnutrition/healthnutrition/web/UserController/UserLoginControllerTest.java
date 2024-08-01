package healthnutrition.healthnutrition.web.UserController;
import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@SpringBootTest
@AutoConfigureMockMvc
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    public void tearDown(){
        userRepositories.deleteAll();
    }

    @Test
    public void testOpenLoginForm() throws Exception {
       mockMvc.perform(get("/users/login")
                ).andExpect(status().isOk())
                .andExpect(view().name("auth-login"));

    }
    @Test
    public void testLogUser() throws Exception {
        addUser();
        MvcResult mvcResult = mockMvc.perform(post("/users/login")
                .param("email", "angel@gmail.com")
                .param("password", "1234")
        ).andExpect(status().is3xxRedirection()).andReturn();
        String location = mvcResult.getResponse().getHeader("Location");
        assertEquals("/home",location);

    }
    @Test
    public void testLogUserNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/users/login")
                .param("email", "angel@gmail.com")
                .param("password", "1234")
        ).andExpect(status().is2xxSuccessful()).andReturn();
        String forwardedUrl = mvcResult.getResponse().getForwardedUrl();
        assertEquals("/users/login-error",forwardedUrl);
    }

    @Test
    public void testOnFailure() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/users/login-error").param("email","angel@gmail.com"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();
        String badCredentials = (String) mvcResult.getModelAndView().getModel().get("bad_credentials");
        boolean bindingResult = mvcResult.getModelAndView().getModel().containsKey("org.springframework.validation.BindingResult.email");
        assertEquals(badCredentials,"true");
        assertTrue(bindingResult);
    }

    private void addUser(){
        User user = new User();
        user.setFullName("Angel");
        user.setPhone("0123456789");
        user.setEmail("angel@gmail.com");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRole(UserRoleEnum.USER);
        userRepositories.save(user);
    }



}