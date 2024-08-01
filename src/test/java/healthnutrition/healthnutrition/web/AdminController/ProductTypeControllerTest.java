package healthnutrition.healthnutrition.web.AdminController;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin",roles = {"ADMIN"})
class ProductTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TypeRepository typeRepository;

    @AfterEach
    public void tearDown(){
        typeRepository.deleteAll();
    }

    @Test
    public void testOpenAddBrand() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/add/type")
                .with(csrf())
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        boolean brand = mvcResult.getModelAndView().getModel().containsKey("typeProductDTO");
        boolean bindingResult = mvcResult.getModelAndView().getModel().containsKey("org.springframework.validation.BindingResult.typeProductDTO");
        assertTrue(brand);
        assertTrue(bindingResult);

    }

    @Test
    public void testAddCorrectBrand() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/add/type")
                .param("type", "PROTEIN")
                .with(csrf())
        ).andExpect(status().is3xxRedirection()).andDo(print()).andReturn();
        assertEquals(1,typeRepository.count());

    }
    @Test
    public void testAddCorrectBrandError() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/add/type")
                .param("type", "")
        ).andExpect(status().is3xxRedirection()).andDo(print()).andReturn();
        assertEquals(0,typeRepository.count());

    }
}