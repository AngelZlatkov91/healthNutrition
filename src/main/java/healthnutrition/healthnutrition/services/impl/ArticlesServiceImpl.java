package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.ArticlesDTO;
import healthnutrition.healthnutrition.models.entitys.Articles;
import healthnutrition.healthnutrition.repositories.ArticlesRepositories;
import healthnutrition.healthnutrition.services.ArticlesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    private ArticlesRepositories articlesRepositories;

    public ArticlesServiceImpl(ArticlesRepositories articlesRepositories) {
        this.articlesRepositories = articlesRepositories;
    }


    @Override
    public void addArticle(ArticlesDTO articlesDTO) {
        Articles articles = new Articles();
        articles.setTitle(articlesDTO.getTitle());
        articles.setDescription(articlesDTO.getDescription());
        articles.setImageUrl(articlesDTO.getImageUrl());
        this.articlesRepositories.save(articles);
    }

    @Override
    public List<ArticlesDTO> allArticles() {
      return this.articlesRepositories.findAll().stream().map(articles ->map(articles)).collect(Collectors.toList());
    }
    private ArticlesDTO map (Articles articles) {
        ArticlesDTO article = new ArticlesDTO();
        article.setTitle(articles.getTitle());
        article.setDescription(articles.getDescription());
        article.setImageUrl(articles.getImageUrl());
        return article;
    }


}
