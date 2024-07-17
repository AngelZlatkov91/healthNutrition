package healthnutrition.healthnutrition.web;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.xml.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
   public void testOpenRegisterForm() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/register")
        ).andExpect(status().isOk())
                .andExpect(view().name("register"));

    }
    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .param("fullName","Kiril ivanov")
                        .param("email", "test@test.com")
                        .param("phone", "0893451813")
                        .param("password", "test")
                        .param("confirmPassword", "test")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void testRegistrationThro() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .accept(MediaType.TEXT_XML)
                        .param("fullName","")
                        .param("email", "")
                        .param("phone","")
                        .param("password", "test")
                        .param("confirmPassword", "test5")

        ).andExpect(status().is4xxClientError());
    }
}