package healthnutrition.healthnutrition.web.AdminController;
import healthnutrition.healthnutrition.models.entitys.Articles;
import healthnutrition.healthnutrition.repositories.ArticlesRepositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.UUID;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin",roles = {"ADMIN"})
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ArticlesRepositories articlesRepositories;
    @AfterEach
    public void tearDown(){
        articlesRepositories.deleteAll();
    }

    @Test
    public void testOpenAddArticle() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/add/article")
                .with(csrf())
        ).andDo(print()).andExpect(status().isOk()).andReturn();
        boolean article = mvcResult.getModelAndView().getModel().containsKey("articlesDTO");
        boolean bindingResult = mvcResult.getModelAndView().getModel().containsKey("org.springframework.validation.BindingResult.articlesDTO");
        assertTrue(article);
        assertTrue(bindingResult);

    }

    @Test
    public void testAddCorrectArticle() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/add/article")
                .param("title", "test Title")
                .param("description", "Long text")
                .param("imageUrl", "picture for the current article")
                .with(csrf())
        ).andExpect(status().is3xxRedirection()).andDo(print()).andReturn();
        assertEquals(1,articlesRepositories.count());

    }
    @Test
    public void testAddCorrectArticleError() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/add/article")
                .param("title", "test Title")
                .param("description", "")
                .param("imageUrl", "")
        ).andExpect(status().is3xxRedirection()).andDo(print()).andReturn();
        assertEquals(0,articlesRepositories.count());
    }
    @Test
    public void testAddArticleWithExistTitle() throws Exception {
        Articles article = createArticle();
        MvcResult mvcResult = mockMvc.perform(post("/add/article")
                .param("title", "TEST TITLE ARTICLE")
                .param("description", "test1")
                .param("imageUrl", "test1")
        ).andExpect(status().is3xxRedirection()).andDo(print()).andReturn();
        assertEquals(1,articlesRepositories.count());
    }


    private Articles createArticle(){
        Articles articles = new Articles();
        articles.setUuid(UUID.randomUUID());
        articles.setDescription("Test article description");
        articles.setImageUrl("test article imageUrl");
        articles.setTitle("TEST TITLE ARTICLE");
        return articlesRepositories.save(articles);
    }



}