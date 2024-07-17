package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.articlesDTOS.ArticlesDTO;
import healthnutrition.healthnutrition.models.entitys.Articles;
import healthnutrition.healthnutrition.repositories.ArticlesRepositories;
import healthnutrition.healthnutrition.services.ArticlesService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    private final ArticlesRepositories articlesRepositories;
    private final ModelMapper mapper;


    public ArticlesServiceImpl(ArticlesRepositories articlesRepositories, ModelMapper mapper) {
        this.articlesRepositories = articlesRepositories;

        this.mapper = mapper;
    }

// add article from admin
    @Override
    public void addArticle(ArticlesDTO articlesDTO) {
        Articles articles = mapper.map(articlesDTO, Articles.class);
        articles.setUuid(UUID.randomUUID());
        this.articlesRepositories.save(articles);
    }

    // get all articles
    @Override
    public Page<ArticlesDTO> allArticles(Pageable pageable) {
      return this.articlesRepositories.findAll(pageable)
              .map(this::map);
    }

    // get article with uuid
    @Override
    public ArticlesDTO getArticle(UUID uuid) {
       Articles articles =  this.articlesRepositories.findByUuid(uuid);
        return map(articles);
    }

    // get random article from repositories with id
    @Override
    public ArticlesDTO getArticle() {
        if (articlesRepositories.count() == 0){
            return null;
        }
        Random random = new Random();
        Long size = random.nextLong(0,this.articlesRepositories.count());
        if (size > this.articlesRepositories.count() || size <= 0) {
            size = this.articlesRepositories.count();
        }

        Optional<Articles> byId = articlesRepositories.findById(size);
        return map(byId.get());
    }
// map article entity to article dto
    private ArticlesDTO map (Articles articles) {
        ArticlesDTO article = this.mapper.map(articles,ArticlesDTO.class);
        article.setId(articles.getUuid().toString());
//        article.setTitle(articles.getTitle());
//        article.setDescription(articles.getDescription());
//        article.setImageUrl(articles.getImageUrl());
        return article;
    }


}
