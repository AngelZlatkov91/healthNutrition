package healthnutrition.healthnutrition.web.UserController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private MockMvc mockMvc;


    @AfterEach
    public  void tearDown(){
        typeRepository.deleteAll();
        brandRepository.deleteAll();
    }

    @Test
    public void givenHomePageURI() throws Exception {
        addBrand();
        addType();
        MvcResult mvcResult = this.mockMvc.perform(get("/home")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        boolean brands = mvcResult.getModelAndView().getModel().containsKey("brands");
        boolean types = mvcResult.getModelAndView().getModel().containsKey("types");
        assertTrue(brands);
        assertTrue(types);
    }


    private BrandProduct addBrand() {
    BrandProduct brand = new BrandProduct();
    brand.setBrand("AMIX");
    brand.setImageUrl("https://www.moremuscle.com/img/m/209.jpg");
    return brandRepository.save(brand);
    }
    private TypeProduct addType(){
        TypeProduct type = new TypeProduct();
        type.setType("PROTEIN");
     return  typeRepository.save(type);
    }


}