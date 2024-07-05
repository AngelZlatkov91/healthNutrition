package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.articlesDTOS.ArticlesDTO;
import healthnutrition.healthnutrition.services.ArticlesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@RestController
public class ArticleController {
    private final ArticlesService articlesService;


    public ArticleController(ArticlesService articlesService) {
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
    @GetMapping("/article/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model) {
        ArticlesDTO articlesDTO = articlesService.getArticle(uuid);
        model.addAttribute("article",articlesDTO);
        return "article-details";
    }

    @GetMapping("/articles/all")
    public String all(Model model, Pageable pageable) {
        Page<ArticlesDTO> allArticles = this.articlesService.allArticles(pageable);
        model.addAttribute("articles",allArticles);
        return "articles";
    }



}
