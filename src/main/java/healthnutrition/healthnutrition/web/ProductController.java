package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.ProductCreateDTO;
import healthnutrition.healthnutrition.services.BrandProductService;
import healthnutrition.healthnutrition.services.ProductService;
import healthnutrition.healthnutrition.services.TypeProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
   private final ProductService productService;
   private final BrandProductService brand;
   private final TypeProductService type;

    public ProductController(ProductService productService, BrandProductService brnd, TypeProductService typeProductService) {
        this.productService = productService;
        this.brand = brnd;
        this.type = typeProductService;
    }



    @GetMapping("/product-add")
    public String add(Model model) {
        if (!model.containsAttribute("productCreateDTO")) {
            model.addAttribute("productCreateDTO", ProductCreateDTO.empty());
        }
        model.addAttribute("brands",brand.allBrands());
        model.addAttribute("types",type.allTypes());
        return "product-add";
    }


    @PostMapping("/product-add")
    public String add(@Valid ProductCreateDTO productCreateDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("productCreateDTO",productCreateDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.productCreateDTO",bindingResult);
            return "redirect:/product-add";
        }
        this.productService.addProduct(productCreateDTO);
        return "redirect:/";
    }

}
