package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.cartDTOS.AllOrdersDTO;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersController {

    private final ShoppingCartService shoppingCartService;

    public OrdersController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping("/orders")
    public ModelAndView orders(Model model) {
        AllOrdersDTO allOrdersDTO = this.shoppingCartService.allOrdersToSend();
        model.addAttribute("allOrders",allOrdersDTO);
        return new ModelAndView("orders");
    }
}
