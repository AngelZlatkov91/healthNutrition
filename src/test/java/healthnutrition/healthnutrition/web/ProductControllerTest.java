package healthnutrition.healthnutrition.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOpenAddProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/product-add")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAddCorrectProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/product-add")
                        .param("name","productName")
                        .param("description", "description for product")
                        .param("price", "50.00")
                        .param("imageUrl", "picture for the current product")
                        .param("type", "type product")
                        .param("brand", "brand product")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }




}