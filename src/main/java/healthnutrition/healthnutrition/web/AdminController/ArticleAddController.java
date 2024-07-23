package healthnutrition.healthnutrition.web.AdminController;

import healthnutrition.healthnutrition.models.dto.articlesDTOS.ArticlesDTO;
import healthnutrition.healthnutrition.services.ArticlesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

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
    @GetMapping("/add/article")
    public ModelAndView addArticle(){
        return new ModelAndView("add-article");
    }

    // add article from admin
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
