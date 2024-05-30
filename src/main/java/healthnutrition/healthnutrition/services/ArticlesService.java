package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ArticlesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ArticlesService {


    void addArticle(ArticlesDTO articlesDTO);

    Page<ArticlesDTO> allArticles(Pageable pageable);

    ArticlesDTO getArticle(UUID uuid);
    ArticlesDTO getArticle();

}
