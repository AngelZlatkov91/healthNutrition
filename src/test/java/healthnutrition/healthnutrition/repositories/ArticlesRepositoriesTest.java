package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.Articles;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArticlesRepositoriesTest {

    @Autowired
    ArticlesRepositories articlesRepositories;

    private Articles articles;

    @BeforeEach
    public void setUp(){
        articles = new Articles();
        articles.setUuid(UUID.randomUUID());
        articles.setTitle("Test Articles");
        articles.setDescription("Test for first project in java web with spring boot");
        articlesRepositories.save(articles);
    }

    @AfterEach
    public void tearDown(){
        articlesRepositories.delete(articles);
    }

    @Test
    public void ArticlesFindByTitle(){
        Optional<Articles> testArticles = articlesRepositories.findByTitle("Test Articles");
        Assertions.assertEquals(testArticles.get().getTitle(),"Test Articles");

    }
    @Test
    public void addSameArticle(){
        articlesRepositories.save(articles);
        assertEquals(1,articlesRepositories.count());
    }
    @Test
    public void ArticlesFindByUUID() {
        Optional<Articles> testArticles = articlesRepositories.findByTitle("Test Articles");
        Articles byUuid = articlesRepositories.findByUuid(testArticles.get().getUuid());

        Assertions.assertEquals(testArticles.get().getTitle(),byUuid.getTitle());

    }

}