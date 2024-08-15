package healthnutrition.healthnutrition.web.AdminController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin",roles = {"ADMIN"})
class ProductAddControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void testGetFormProductAdd() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/product-add"))
                .andExpect(status().isOk()).andReturn();
        boolean brands = mvcResult.getModelAndView().getModel().containsKey("brands");
        boolean types = mvcResult.getModelAndView().getModel().containsKey("types");
        boolean productCreateDTO = mvcResult.getModelAndView().getModel().containsKey("productCreateDTO");
        assertTrue(brands);
        assertTrue(types);
        assertTrue(productCreateDTO);
    }

    @Test
    public void testPostProductWithNotValidData() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/product-add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                 {
                    "name" : "",
                    "description" : "Test description",
                    "price" : 50.00,
                    "imageUrl" : "https://nowfoods.bg/image/catalog/4721_mainimage_1.jpg" ,
                    "type" : "",
                    "brand" : ""
                 }
                """))
                .andExpect(status().is3xxRedirection()).andReturn();
    }

    @Test
    public void testGetEditPriceProduct() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/product/edit/price"))
                .andExpect(status().isOk()).andReturn();
        boolean editPrice = mvcResult.getModelAndView().getModel().containsKey("editPrice");
        assertTrue(editPrice);
    }
}