package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.ArticlesDTO;
import healthnutrition.healthnutrition.services.ArticlesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticlesController {
    private final ArticlesService articlesService;

    public ArticlesController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }
    @GetMapping("/articles/all")
    public String all(Model model, Pageable pageable) {
        Page<ArticlesDTO> allArticles = this.articlesService.allArticles(pageable);
        model.addAttribute("articles",allArticles);
        return "articles";
    }
}
