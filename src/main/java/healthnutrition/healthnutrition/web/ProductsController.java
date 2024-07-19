package healthnutrition.healthnutrition.web;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/all")
    public ModelAndView all(Model model) {
        List<ProductDetailsDTO> allProducts = productService.getAllProducts("");
        model.addAttribute("products",allProducts);
        return new ModelAndView( "products");
    }




}
