package healthnutrition.healthnutrition.web.UserController;
import healthnutrition.healthnutrition.models.dto.articlesDTOS.ArticlesDTO;
import healthnutrition.healthnutrition.models.entitys.Articles;
import healthnutrition.healthnutrition.repositories.ArticlesRepositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class ArticleDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticlesRepositories articlesRepositories;

    @AfterEach
    public void tearDown(){
        articlesRepositories.deleteAll();
    }

    @Test
    public void testGetArticleByUUID() throws Exception {
        Articles article = createArticle();
        MvcResult mvcResult = mockMvc.perform(get("/article/{uuid}", article.getUuid()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ArticlesDTO articlesDTO = (ArticlesDTO) mvcResult.getModelAndView().getModel().get("article");
        assertEquals(article.getTitle(),articlesDTO.getTitle());

    }
    @Test
    public void testGetAllArticle() throws Exception {
        Articles article = createArticle();
        MvcResult mvcResult = mockMvc.perform(get("/articles/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Page<ArticlesDTO> allArticles = (Page<ArticlesDTO>) mvcResult.getModelAndView().getModel().get("articles");
        long count = allArticles.stream().count();
        assertEquals(1,count);

    }



    private Articles createArticle(){
        Articles articles = new Articles();
        articles.setUuid(UUID.randomUUID());
        articles.setDescription("Test article description");
        articles.setTitle("TEST TITLE ARTICLE");
        return articlesRepositories.save(articles);
    }

}