package healthnutrition.healthnutrition.web.UserController;
import healthnutrition.healthnutrition.models.dto.articlesDTOS.ArticlesDTO;
import healthnutrition.healthnutrition.services.ArticlesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import java.util.UUID;

@Controller
public class ArticleDetailsController {
    private final ArticlesService articlesService;

    public ArticleDetailsController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }
    // get article by uuid
    @GetMapping("/article/{uuid}")
    public ModelAndView details(@PathVariable("uuid") UUID uuid, Model model) {
        ArticlesDTO articlesDTO = articlesService.getArticle(uuid);
        model.addAttribute("article",articlesDTO);
        return new ModelAndView( "article-details");
    }
     // get all articles
    @GetMapping("/articles/all")
    public ModelAndView all(Model model, Pageable pageable) {
        Page<ArticlesDTO> allArticles = this.articlesService.allArticles(pageable);
        model.addAttribute("articles",allArticles);
        return new ModelAndView("articles");
    }
}
