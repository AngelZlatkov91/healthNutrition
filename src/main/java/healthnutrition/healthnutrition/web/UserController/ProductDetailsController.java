package healthnutrition.healthnutrition.web.UserController;

import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.ProductService;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductDetailsController {
    private final ProductService productService;
    private  final ShoppingCartService shoppingCartService;

    public ProductDetailsController(ProductService productService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }
    // get all products
    @GetMapping("/products/all")
    public ModelAndView all(Model model) {
        List<ProductDetailsDTO> allProducts = productService.getAllProducts("");
        model.addAttribute("products",allProducts);
        return new ModelAndView( "products");
    }
     // get product by uuid
    @GetMapping("/product/{uuid}")
    public ModelAndView details(@PathVariable("uuid") UUID uuid, Model model) {
        ProductDetailsDTO productDetailsDTO = productService.getProductDetails(uuid);
        model.addAttribute("product",productDetailsDTO);
        return new ModelAndView("details");
    }
    // add product to shopping cart
    @GetMapping("/product/add-shoppingCart/{uuid}")
    public ModelAndView addProductToCart(@PathVariable("uuid") UUID uuid) {
        this.shoppingCartService.addProductToShoppingCart(uuid);
        return new ModelAndView("redirect:/shopping_cart");
    }

}
