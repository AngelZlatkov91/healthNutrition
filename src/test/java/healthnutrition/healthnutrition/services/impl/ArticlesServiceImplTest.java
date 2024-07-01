package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.articlesDTOS.ArticlesDTO;
import healthnutrition.healthnutrition.models.entitys.Articles;
import healthnutrition.healthnutrition.repositories.ArticlesRepositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticlesServiceImplTest {

    @Autowired
    private ArticlesServiceImpl articlesService;
    @Autowired
    private ArticlesRepositories articlesRepositories;


    @BeforeEach
    void setUp(){
        articlesRepositories.deleteAll();
    }
    @AfterEach
    void cleanUp(){
        articlesRepositories.deleteAll();
    }

    @Test
    public void addArticle(){
        ArticlesDTO articlesDTO = articlesDTO();
        articlesService.addArticle(articlesDTO);
        assertEquals(1,articlesRepositories.count());
    }
    @Test
    public void addArticleWithExistingTitle(){
        ArticlesDTO articlesDTO = articlesDTO();
        articlesService.addArticle(articlesDTO);
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            articlesService.addArticle(articlesDTO);
        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getArticleWithUUID(){
        ArticlesDTO articlesDTO = articlesDTO();
        articlesService.addArticle(articlesDTO);
        Optional<Articles> byTitle = articlesRepositories.findByTitle(articlesDTO.getTitle());
        ArticlesDTO article = articlesService.getArticle(byTitle.get().getUuid());
        assertEquals(article.getId(),byTitle.get().getUuid().toString());
    }
    @Test
    public void getAllArticles() {
        ArticlesDTO articlesDTO = articlesDTO();
        articlesService.addArticle(articlesDTO);
        Pageable pageable =Pageable.unpaged();
        Page<ArticlesDTO> articlesDTOS = articlesService.allArticles(pageable);
        assertEquals(1,articlesDTOS.stream().count());
    }



    private ArticlesDTO articlesDTO(){
        ArticlesDTO articlesDTO = new ArticlesDTO();
        articlesDTO.setTitle("Test Article Service");
        articlesDTO.setDescription("text description for title");
        articlesDTO.setImageUrl("random picture");
        return articlesDTO;
    }
}