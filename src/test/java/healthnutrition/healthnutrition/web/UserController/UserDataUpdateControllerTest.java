package healthnutrition.healthnutrition.web.UserController;

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
@WithMockUser(username = "angel@gmail.com",roles = {"USER"})
class UserDataUpdateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepositories userRepositories;

    @Test
    public void testGetProfileForm() throws Exception {
        addUser1();
        MvcResult mvcResult =
                mockMvc.perform(get("/profile"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        boolean archive = mvcResult.getModelAndView().getModel().containsKey("archive");
        boolean user = mvcResult.getModelAndView().getModel().containsKey("user");
          assertTrue(archive);
          assertTrue(user);
    }
    @Test
    public void testGetEditForm() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        boolean editUserDTO = mvcResult.getModelAndView().getModel().containsKey("editUserDTO");
        boolean bindingResult = mvcResult.getModelAndView().getModel().containsKey("org.springframework.validation.BindingResult.editUserDTO");
       assertTrue(editUserDTO);
       assertTrue(bindingResult);
    }

    @Test
    public void testEditUserData() throws Exception {
       mockMvc.perform(post("/edit")
                        .param("fullName","Zlatkov")
                        .param("email","ango_z@abv.bg")
                        .param("phone","0987654321"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        Optional<User> byEmail = userRepositories.findByEmail("ango_z@abv.bg");
        assertFalse(byEmail.isPresent());

    }
    @Test
    public void testEditUserDataNotInvalidParam() throws Exception {
        addUser2();
        mockMvc.perform(post("/edit")
                        .param("fullName","Zlatkov")
                        .param("email","")
                        .param("phone","1122334455"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        Optional<User> byEmail = userRepositories.findByEmail("angel@gmail.com");
        assertTrue(byEmail.isPresent());
        assertEquals("0123456789",byEmail.get().getPhone());
    }




    private void addUser1(){
        User user = new User();
        user.setFullName("Angel");
        user.setPhone("0123456789");
        user.setEmail("angel@gmail.com");
        user.setPassword("1234");
        user.setRole(UserRoleEnum.USER);
        userRepositories.save(user);
    }

    private void addUser2(){
        User user = new User();
        user.setFullName("TEST");
        user.setPhone("1122334455");
        user.setEmail("test@gmail.com");
        user.setPassword("1234");
        user.setRole(UserRoleEnum.USER);
        userRepositories.save(user);
    }
}