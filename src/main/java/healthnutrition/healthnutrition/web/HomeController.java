package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.ArticlesDTO;
import healthnutrition.healthnutrition.models.dto.GetBrandsDTO;
import healthnutrition.healthnutrition.models.dto.GetTypesDTO;
import healthnutrition.healthnutrition.models.dto.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.ArticlesService;
import healthnutrition.healthnutrition.services.BrandProductService;
import healthnutrition.healthnutrition.services.ProductService;
import healthnutrition.healthnutrition.services.TypeProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

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



    @GetMapping("/home")
    public String home(Model model){
        List<GetBrandsDTO> brands = brandProductService.allBrands();
        List<GetTypesDTO> types = typeProductService.allTypes();
        ArticlesDTO article = articlesService.getArticle();
        model.addAttribute("brands", brands);
        model.addAttribute("types",types);
        model.addAttribute("article",article);
        return "home";
    }

    @GetMapping("/search")
    public  String search(Model model, @PathVariable("searchKey")@RequestParam(defaultValue = "") String searchKey) {
        model.addAttribute("searchKey",searchKey);

        List<ProductDetailsDTO> allProducts = productService.getAllProducts(searchKey);
        model.addAttribute("products",allProducts);
        return "search";
    }

    @GetMapping("/search/{searchKey}")
    public  String searchBy(Model model, @PathVariable("searchKey") String searchKey) {
        model.addAttribute("searchKey",searchKey);
        List<ProductDetailsDTO> allProducts = productService.getAllProducts(searchKey);
        model.addAttribute("products",allProducts);
        return "search";
    }



}
