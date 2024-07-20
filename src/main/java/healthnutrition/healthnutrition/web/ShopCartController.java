package healthnutrition.healthnutrition.web;
import healthnutrition.healthnutrition.models.dto.cartDTOS.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ShoppingCartDTO;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;


@Controller
public class ShopCartController {

    private final ShoppingCartService shoppingCartService;


    public ShopCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;

    }



    @GetMapping("/shopping_cart")
    public ModelAndView shoppingCart(Model model){
        ShoppingCartDTO shoppingCartDTO = this.shoppingCartService.productInCart();
        List<ProductInCartDTO> productFromShoppingCart = shoppingCartDTO.getProductFromShoppingCart();
        double priceForProducts = this.shoppingCartService.calculateTotalPrice();
        model.addAttribute("price",priceForProducts);
        return new ModelAndView("shoping_cart","products",productFromShoppingCart);
    }



    @GetMapping("/remove/product/{getName}")
    public ModelAndView removeProduct(@PathVariable("getName") String getName) {
        this.shoppingCartService.remove(getName);
        return new ModelAndView("redirect:/shopping_cart");
    }
    @GetMapping("/decrease/product/{getName}")
    public ModelAndView decreaseProduct(@PathVariable("getName") String getName){
        this.shoppingCartService.decrease(getName);
        return new ModelAndView("redirect:/shopping_cart");
    }
    @GetMapping("/increase/product/{getName}")
    public ModelAndView increaseProduct(@PathVariable("getName") String getName){
        this.shoppingCartService.increase(getName);
        return new ModelAndView("redirect:/shopping_cart");
    }









}
