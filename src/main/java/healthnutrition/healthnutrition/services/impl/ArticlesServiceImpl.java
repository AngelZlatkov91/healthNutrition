package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.ArticlesDTO;
import healthnutrition.healthnutrition.models.entitys.Articles;
import healthnutrition.healthnutrition.repositories.ArticlesRepositories;
import healthnutrition.healthnutrition.services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    private final ArticlesRepositories articlesRepositories;



    public ArticlesServiceImpl(ArticlesRepositories articlesRepositories) {
        this.articlesRepositories = articlesRepositories;

    }


    @Override
    public void addArticle(ArticlesDTO articlesDTO) {
        Articles articles = new Articles();
        articles.setUuid(UUID.randomUUID());
        articles.setTitle(articlesDTO.getTitle());
        articles.setDescription(articlesDTO.getDescription());
        articles.setImageUrl(articlesDTO.getImageUrl());
        this.articlesRepositories.save(articles);
    }

    @Override
    public Page<ArticlesDTO> allArticles(Pageable pageable) {
      return this.articlesRepositories.findAll(pageable)
              .map(this::map);
    }

    @Override
    public ArticlesDTO getArticle(UUID uuid) {
       Articles articles =  this.articlesRepositories.findByUuid(uuid);
        return map(articles);
    }

    @Override
    public ArticlesDTO getArticle() {
        Random random = new Random();
        Long size = random.nextLong(0,this.articlesRepositories.count());
        if (size > this.articlesRepositories.count() || size <= 0) {
            size = this.articlesRepositories.count();
        }
        Optional<Articles> byId = articlesRepositories.findById(size);
        return map(byId.get());
    }

    private ArticlesDTO map (Articles articles) {
        ArticlesDTO article = new ArticlesDTO();
        article.setId(articles.getUuid().toString());
        article.setTitle(articles.getTitle());
        article.setDescription(articles.getDescription());
        article.setImageUrl(articles.getImageUrl());
        return article;
    }


}
