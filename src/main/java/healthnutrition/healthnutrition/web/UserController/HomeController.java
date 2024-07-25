package healthnutrition.healthnutrition.web.UserController;

import healthnutrition.healthnutrition.config.MyScheduledTasks;
import healthnutrition.healthnutrition.models.dto.articlesDTOS.ArticlesDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.GetBrandsDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.GetTypesDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;
    private final ArticlesService articlesService;
    private final BrandProductService brandProductService;
    private final TypeProductService typeProductService;


    public HomeController(ProductService productService, ArticlesService articlesService, BrandProductService brandProductService, TypeProductService typeProductService) {
        this.productService = productService;
        this.articlesService = articlesService;
        this.brandProductService = brandProductService;
        this.typeProductService = typeProductService;
    }

    // home view
    @GetMapping("/home")
    public ModelAndView home(Model model){
        List<GetBrandsDTO> brands = brandProductService.allBrands();
        List<GetTypesDTO> types = typeProductService.allTypes();
        ArticlesDTO article = articlesService.getArticle();
        model.addAttribute("article",article);
        model.addAttribute("brands", brands);
        model.addAttribute("types",types);
        return new ModelAndView("home");
    }
    // get product by search key
    @GetMapping("/search/{searchKey}")
    public  ModelAndView searchBy( @PathVariable("searchKey") @RequestParam(defaultValue = "") String searchKey, Model model) {
        model.addAttribute("searchKey",searchKey);
        List<ProductDetailsDTO> allProducts = productService.getAllProducts(searchKey);
        model.addAttribute("products",allProducts);
        return new ModelAndView("search");
    }
     // get all product by brand
    @GetMapping("/brand/{searchKey}")
    public  ModelAndView searchByBrand( @PathVariable("searchKey")  String searchKey, Model model) {
        model.addAttribute("searchKey",searchKey);
        List<ProductDetailsDTO> allProducts = productService.getAllProducts(searchKey);
        model.addAttribute("products",allProducts);
        return new ModelAndView("search");
    }
    // get all product by type
    @GetMapping("/type/{searchKey}")
    public  ModelAndView searchByType( @PathVariable("searchKey")  String searchKey, Model model) {
        model.addAttribute("searchKey",searchKey);
        List<ProductDetailsDTO> allProducts = productService.getAllProducts(searchKey);
        model.addAttribute("products",allProducts);
        return new ModelAndView("search");
    }




}
