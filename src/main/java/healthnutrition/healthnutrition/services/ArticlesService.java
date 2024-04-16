package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.ArticlesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticlesService {


    void addArticle(ArticlesDTO articlesDTO);

    Page<ArticlesDTO> allArticles(Pageable pageable);
}
