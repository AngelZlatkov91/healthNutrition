package healthnutrition.healthnutrition.web;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOpenAddArticleForm() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/add/article")
                                .with(csrf())
                ).andExpect(status().is3xxRedirection());

    }

    @Test
    public void testAddCorrectArticle() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add/article")
                        .param("title","first article")
                        .param("description", "test first article")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }
    @Test
    public void testAddCorrectArticleThrow() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add/article")
                        .accept(MediaType.TEXT_XML)
                        .param("title","")
                        .param("description", "test first article")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }



}