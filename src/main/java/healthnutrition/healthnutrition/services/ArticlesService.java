package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ArticlesDTO;

import java.util.List;

public interface ArticlesService {


    void addArticle(ArticlesDTO articlesDTO);

    List<ArticlesDTO> allArticles();
}
