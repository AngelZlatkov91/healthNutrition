package healthnutrition.healthnutrition.web;
import healthnutrition.healthnutrition.models.dto.*;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import healthnutrition.healthnutrition.services.DeliveryDataService;
import healthnutrition.healthnutrition.services.DeliveryFirmService;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;


@Controller
public class ShopCartController {

    private final ShoppingCartService shoppingCartService;

    private final DeliveryDataService deliveryDataService;
    private final DeliveryFirmService deliveryFirmService;

    public ShopCartController(ShoppingCartService shoppingCartService, DeliveryDataService deliveryDataService, DeliveryFirmService deliveryFirmService) {
        this.shoppingCartService = shoppingCartService;
        this.deliveryDataService = deliveryDataService;
        this.deliveryFirmService = deliveryFirmService;
    }

    @ModelAttribute("firm")
    public DeliveryFirmDTO deliveryFirmDTO(){
        return new DeliveryFirmDTO();
    }
    @ModelAttribute("data")
    public DeliveryDataDTO deliveryDataDTO(){
        return new DeliveryDataDTO();
    }
    @ModelAttribute("nameFirm")
    public DeliveryFirmEnum[] firmValues(){
        return DeliveryFirmEnum.values();
    }
    @ModelAttribute("address")
    public DeliveryAddress[] addressType(){
        return  DeliveryAddress.values();
    }


    @GetMapping("/shopping_cart")
    public ModelAndView shoppingCart(Model model){
        ShoppingCartDTO shoppingCartDTO = this.shoppingCartService.productInCart();
        List<ProductInCartDTO> productFromShoppingCart = shoppingCartDTO.getProductFromShoppingCart();
        double price = this.shoppingCartService.calculateTotalPrice();
        model.addAttribute("price",price);
        return new ModelAndView("shoping_cart","products",productFromShoppingCart);
    }

    @GetMapping("/delivery")
    public String finalDelivery() {
        return "delivery";
    }


    @PostMapping("/delivery")
    public String finalDelivery( DeliveryFirmDTO firm,DeliveryDataDTO data, UserDetails user,
                                      //BindingResult bindingResult,
                                      //RedirectAttributes redirectAttributes,
                                       Model model) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.firm",bindingResult);
//            redirectAttributes.addFlashAttribute("firm", firm);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.data",bindingResult);
//            redirectAttributes.addFlashAttribute("data", data);
//            return new ModelAndView("redirect:/register");
//        }
        this.deliveryDataService.addAddress(data,user);


        this.deliveryFirmService.addDeliverFirm(user,firm);
        double price = this.deliveryFirmService.getPrice();
       // model.addAttribute("price",price);
        UUID uuid = this.shoppingCartService.finalStep();
        System.out.println();
         return "redirect:/succsesDelivery";
    }



}
