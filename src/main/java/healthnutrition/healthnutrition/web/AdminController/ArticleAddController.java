package healthnutrition.healthnutrition.web.AdminController;
import healthnutrition.healthnutrition.models.dto.articlesDTOS.ArticlesDTO;
import healthnutrition.healthnutrition.services.ArticlesService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class ArticleAddController {
    private final ArticlesService articlesService;

    public ArticleAddController(ArticlesService articlesService) {
        this.articlesService = articlesService;

    }
    @ModelAttribute("articlesDTO")
    public ArticlesDTO initForm(){
        return new ArticlesDTO();
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/add/article")
    public ModelAndView addArticle(){
        return new ModelAndView("add-article");
    }

    // add article from admin
    @Secured("ROLE_ADMIN")
    @PostMapping("/add/article")
    public ModelAndView addArticle(@Valid ArticlesDTO articlesDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes rAtt){

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("articlesDTO",articlesDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.articlesDTO",bindingResult);
            return new ModelAndView("redirect:/add/article");
        }
        this.articlesService.addArticle(articlesDTO);
        return  new ModelAndView("redirect:/home");
    }

}
