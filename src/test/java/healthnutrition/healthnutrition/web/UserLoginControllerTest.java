package healthnutrition.healthnutrition.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testOpenRegisterForm() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/users/login")
                ).andExpect(status().isOk())
                .andExpect(view().name("auth-login"));

    }
    @Test
    public void testLogUser() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")

                        .param("email","ivan@abv.bg")
                        .param("password","1324")
                        .with(csrf())

        ).andExpect(status().is2xxSuccessful());
    }



}