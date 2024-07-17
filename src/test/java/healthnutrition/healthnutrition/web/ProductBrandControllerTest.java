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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class ProductBrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOpenAddBrand() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/add/brand")
                                .with(csrf())
                ).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAddCorrectBrand() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add/brand")
                        .param("brand","amix")
                        .param("imageUrl", "picture for the current brand")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }
    @Test
    public void testAddCorrectBrandError() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add/brand")
                        .param("brand","")
                        .param("imageUrl", "")
        ).andExpect(status().is4xxClientError());
    }
}