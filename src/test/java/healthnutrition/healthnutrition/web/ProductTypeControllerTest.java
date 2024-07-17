package healthnutrition.healthnutrition.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOpenAddType() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/add/type")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAddCorrectType() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add/type")
                        .param("type","protein")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }
    @Test
    public void testAddCorrectTypeError() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add/type")
                        .param("type","")
        ).andExpect(status().is4xxClientError());
    }
}