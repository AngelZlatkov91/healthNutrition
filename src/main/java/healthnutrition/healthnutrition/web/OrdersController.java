package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.AllOrdersDTO;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

    private ShoppingCartService shoppingCartService;

    public OrdersController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping("/orders")
    public String orders(Model model) {
        AllOrdersDTO allOrdersDTO = this.shoppingCartService.allOrdersToSend();
        model.addAttribute("allOrders",allOrdersDTO);
        return "orders";
    }
}
