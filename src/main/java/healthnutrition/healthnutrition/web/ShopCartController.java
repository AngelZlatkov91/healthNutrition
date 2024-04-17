package healthnutrition.healthnutrition.web;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.stereotype.Controller;


@Controller
public class ShopCartController {

    private final ShoppingCartService shoppingCartService;

    public ShopCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


}
