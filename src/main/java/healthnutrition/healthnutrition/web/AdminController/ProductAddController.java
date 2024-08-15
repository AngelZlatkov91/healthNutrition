package healthnutrition.healthnutrition.web.AdminController;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductEditPrice;
import healthnutrition.healthnutrition.services.BrandProductService;
import healthnutrition.healthnutrition.services.RestProductService;
import healthnutrition.healthnutrition.services.TypeProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductAddController {

   private final RestProductService restProductService;
   private final BrandProductService brand;
   private final TypeProductService type;

    public ProductAddController( RestProductService restProductService, BrandProductService brand, TypeProductService typeProductService) {
        this.restProductService = restProductService;
        this.brand = brand;
        this.type = typeProductService;
    }
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
    @PostMapping( "/product-add")
    public ModelAndView add(@Valid ProductCreateDTO productCreateDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt){
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("productCreateDTO",productCreateDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.productCreateDTO",bindingResult);
            return new ModelAndView("redirect:/product-add");
        }
        this.restProductService.addProduct(productCreateDTO);
        return new ModelAndView("redirect:/product-add");
    }
   // delete product form admin
   @Secured("ROLE_ADMIN")
    @DeleteMapping("/product/remove/{name}")
    public ModelAndView delete(@PathVariable("name") String name) {
        restProductService.deleteProduct(name);
        return new ModelAndView("redirect:/products/all");
    }

    @ModelAttribute("editPrice")
    public ProductEditPrice productEditPrice(){
        return new ProductEditPrice();
    }

      @GetMapping("/product/edit/price")
      public ModelAndView editProductPrice() {
        return new ModelAndView("edit-price");
      }
      @Secured("ROLE_ADMIN")
      @PostMapping("/product/edit/price")
    public ModelAndView editProductPrice(@Valid ProductEditPrice editPrice,
                                         BindingResult bindingResult,
                                         RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("editPrice",editPrice);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.editPrice",bindingResult);
            return new ModelAndView("redirect:/product/edit/price");
        }
        this.restProductService.editPrice(editPrice);
        return new ModelAndView("redirect:/products/all");
    }

}
