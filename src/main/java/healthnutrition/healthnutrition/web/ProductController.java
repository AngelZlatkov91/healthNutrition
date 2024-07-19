package healthnutrition.healthnutrition.web;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.BrandProductService;
import healthnutrition.healthnutrition.services.ProductService;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import healthnutrition.healthnutrition.services.TypeProductService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller
public class ProductController {
   private final ProductService productService;
   private final BrandProductService brand;
   private final TypeProductService type;
   private  final ShoppingCartService shoppingCartService;

    public ProductController(ProductService productService, BrandProductService brand, TypeProductService typeProductService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.brand = brand;
        this.type = typeProductService;
        this.shoppingCartService = shoppingCartService;

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


    @PostMapping( "/product-add")
    public ModelAndView add(@Valid ProductCreateDTO productCreateDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt){
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("productCreateDTO",productCreateDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.productCreateDTO",bindingResult);
            return new ModelAndView("redirect:/product-add");
        }
        this.productService.addProduct(productCreateDTO);
        return new ModelAndView("redirect:/product-add");
    }

    @GetMapping("/product/{uuid}")
    public ModelAndView details(@PathVariable("uuid") UUID uuid, Model model) {
        ProductDetailsDTO productDetailsDTO = productService.getProductDetails(uuid);
        model.addAttribute("product",productDetailsDTO);
        return new ModelAndView("details");
    }
    @GetMapping("/product/add-shoppingCart/{uuid}")
    public ModelAndView addProductToCart(@PathVariable("uuid") UUID uuid) {
        this.shoppingCartService.addProductToShoppingCart(uuid);
        return new ModelAndView("redirect:/products/all");
    }

    @DeleteMapping("/product/remove/{uuid}")
    public ModelAndView delete(@PathVariable("uuid") UUID uuid) {
        productService.deleteProduct(uuid);
        return new ModelAndView("redirect:/products/all");
    }
}
