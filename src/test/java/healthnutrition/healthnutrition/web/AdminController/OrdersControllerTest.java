package healthnutrition.healthnutrition.web.AdminController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin",roles = {"ADMIN"})
class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetOrders() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        boolean orders = mvcResult.getModelAndView().getModel().containsKey("allOrders");
        assertTrue(orders);
    }


}