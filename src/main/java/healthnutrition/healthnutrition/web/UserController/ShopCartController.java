package healthnutrition.healthnutrition.web.UserController;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ShoppingCartDTO;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Controller
public class ShopCartController {
    private final ShoppingCartService shoppingCartService;

    public ShopCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

// get template for view shopping cart
    @GetMapping("/shopping_cart")
    public ModelAndView shoppingCart(Model model){
        ShoppingCartDTO shoppingCartDTO = this.shoppingCartService.productInCart();
        List<ProductInCartDTO> productFromShoppingCart = shoppingCartDTO.getProductFromShoppingCart();
        double priceForProducts = this.shoppingCartService.calculateTotalPrice();
        model.addAttribute("price",priceForProducts);
        return new ModelAndView("shoping_cart","products",productFromShoppingCart);
    }


     // remove product from cart
    @GetMapping("/remove/product/{getName}")
    public ModelAndView removeProduct(@PathVariable("getName") String getName) {
        this.shoppingCartService.remove(getName);
        return new ModelAndView("redirect:/shopping_cart");
    }
    // decrease quantity product from cart
    @GetMapping("/decrease/product/{getName}")
    public ModelAndView decreaseProduct(@PathVariable("getName") String getName){
        this.shoppingCartService.decrease(getName);
        return new ModelAndView("redirect:/shopping_cart");
    }
    // increase quantity product from cart
    @GetMapping("/increase/product/{getName}")
    public ModelAndView increaseProduct(@PathVariable("getName") String getName){
        this.shoppingCartService.increase(getName);
        return new ModelAndView("redirect:/shopping_cart");
    }

}
