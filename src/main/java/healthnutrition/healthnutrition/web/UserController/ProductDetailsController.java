package healthnutrition.healthnutrition.web.UserController;

import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.RestProductService;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Controller
public class ProductDetailsController {
    private  final ShoppingCartService shoppingCartService;
    private final RestProductService restProductService;

    public ProductDetailsController(ShoppingCartService shoppingCartService, RestProductService restProductService) {
        this.shoppingCartService = shoppingCartService;
        this.restProductService = restProductService;
    }
    // get all products
    @GetMapping("/products/all")
    public ModelAndView all(Model model) {
        List<ProductDetailsDTO> products = restProductService.getAllProducts();
        model.addAttribute("products",products);
        return new ModelAndView( "products");
    }
     // get product by uuid
    @GetMapping("/product/{name}")
    public ModelAndView details(@PathVariable("name") String name, Model model) {
        ProductDetailsDTO productByName= restProductService.getProductByName(name);
        model.addAttribute("productByName",productByName);
        return new ModelAndView("details");
    }
    // add product to shopping cart
    @GetMapping("/product/add-shoppingCart/{name}")
    public ModelAndView addProductToCart(@PathVariable("name") String name) {
        this.shoppingCartService.addProductToShoppingCart(name);
        return new ModelAndView("redirect:/shopping_cart");
    }

}
