package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.ArticlesDTO;
import healthnutrition.healthnutrition.models.entitys.Articles;
import healthnutrition.healthnutrition.repositories.ArticlesRepositories;
import healthnutrition.healthnutrition.services.ArticlesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private ArticlesDTO map (Articles articles) {
        ArticlesDTO article = new ArticlesDTO();
        article.setId(articles.getUuid().toString());
        article.setTitle(articles.getTitle());
        article.setDescription(articles.getDescription());
        article.setImageUrl(articles.getImageUrl());
        return article;
    }


}
