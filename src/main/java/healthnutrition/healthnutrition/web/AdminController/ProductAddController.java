package healthnutrition.healthnutrition.web.AdminController;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.services.BrandProductService;
import healthnutrition.healthnutrition.services.ProductService;
import healthnutrition.healthnutrition.services.RestProductService;
import healthnutrition.healthnutrition.services.TypeProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.UUID;

@Controller
public class ProductAddController {
   private final ProductService productService;
   private final RestProductService restProductService;
   private final BrandProductService brand;
   private final TypeProductService type;

    public ProductAddController(ProductService productService, RestProductService restProductService, BrandProductService brand, TypeProductService typeProductService) {
        this.productService = productService;
        this.restProductService = restProductService;
        this.brand = brand;
        this.type = typeProductService;
    }
    @GetMapping("/product-add")
    public ModelAndView add(Model model) {
        if (!model.containsAttribute("productCreateDTO")) {
            model.addAttribute("productCreateDTO", ProductCreateDTO.empty());
        }
        model.addAttribute("brands",brand.allBrands());
        model.addAttribute("types",type.allTypes());
        return new ModelAndView("product-add");
    }

    // add product from admin
    @PostMapping( "/product-add")
    public ModelAndView add(@Valid ProductCreateDTO productCreateDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt){
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("productCreateDTO",productCreateDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.productCreateDTO",bindingResult);
            return new ModelAndView("redirect:/product-add");
        }
        Long l = restProductService.addProduct(productCreateDTO);
        this.productService.addProduct(productCreateDTO);
        return new ModelAndView("redirect:/product-add");
    }
   // delete product form admin
    @DeleteMapping("/product/remove/{uuid}")
    public ModelAndView delete(@PathVariable("uuid") UUID uuid) {
        productService.deleteProduct(uuid);
        return new ModelAndView("redirect:/products/all");
    }

}
