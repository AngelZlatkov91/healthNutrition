package healthnutrition.healthnutrition.web.UserController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFormProductAdd() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/products/all"))
                .andExpect(status().isOk()).andReturn();
        boolean products = mvcResult.getModelAndView().getModel().containsKey("products");
        assertTrue(products);

    }
}