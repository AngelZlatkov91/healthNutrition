package healthnutrition.healthnutrition.web.AdminController;

import healthnutrition.healthnutrition.models.dto.cartDTOS.AllOrdersDTO;
import healthnutrition.healthnutrition.services.ArchiveShoppingCartService;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersController {

    private final ArchiveShoppingCartService archiveShoppingCartService;

    public OrdersController(ArchiveShoppingCartService archiveShoppingCartService) {
        this.archiveShoppingCartService = archiveShoppingCartService;
    }

    // view for  all orders for admin
    @GetMapping("/orders")
    public ModelAndView orders(Model model) {
        AllOrdersDTO allOrdersDTO = this.archiveShoppingCartService.allOrdersToSend();
        model.addAttribute("allOrders",allOrdersDTO);
        return new ModelAndView("orders");
    }
}
