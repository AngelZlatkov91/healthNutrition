package healthnutrition.healthnutrition.web.AdminController;

import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "angel@gmail.com",roles = {"ADMIN"})
class UserRoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepositories userRepositories;

    @Test
    public void testGetAllUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/edit/admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        boolean userRoleDTOS = mvcResult.getModelAndView().getModel().containsKey("userRoleDTOS");
        assertTrue(userRoleDTOS);
    }
    @Test
    public void testSetUserToRoleAdmin() throws Exception {
        userRepositories.save(newUSer_Role_USER());
        MvcResult mvcResult = mockMvc.perform(post("/make-admin")
                        .param("email", newUSer_Role_USER().getEmail()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Optional<User> byEmail = userRepositories.findByEmail(newUSer_Role_USER().getEmail());
        assertEquals("ADMIN",byEmail.get().getRole().name());
    }
    @Test
    public void testSetUserToRoleUSER() throws Exception {
        userRepositories.save(newUSer_Role_ADMIN());
        MvcResult mvcResult = mockMvc.perform(post("/remove-admin")
                        .param("email", newUSer_Role_ADMIN().getEmail()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();
        Optional<User> byEmail = userRepositories.findByEmail(newUSer_Role_ADMIN().getEmail());
        assertEquals("USER",byEmail.get().getRole().name());
    }


    private User newUSer_Role_USER(){
        User user = new User();
        user.setFullName("test User");
        user.setEmail("test@User.gmail");
        user.setPhone("123456798");
        user.setPassword("12345");
        user.setRole(UserRoleEnum.USER);
        return user;
    }

    private User newUSer_Role_ADMIN(){
        User user = new User();
        user.setFullName("test ADMIN");
        user.setEmail("test@ADMIN.gmail");
        user.setPhone("1324657922");
        user.setPassword("12345");
        user.setRole(UserRoleEnum.ADMIN);
        return user;
    }

}