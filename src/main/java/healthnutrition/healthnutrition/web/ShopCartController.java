package healthnutrition.healthnutrition.web;
import healthnutrition.healthnutrition.models.dto.*;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import healthnutrition.healthnutrition.services.DeliveryDataService;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Controller
public class ShopCartController {

    private final ShoppingCartService shoppingCartService;

    private final DeliveryDataService deliveryDataService;
    private double price;
    private UUID uuid;

    public ShopCartController(ShoppingCartService shoppingCartService, DeliveryDataService deliveryDataService) {
        this.shoppingCartService = shoppingCartService;
        this.deliveryDataService = deliveryDataService;
        this.price = 0.0;
        this.uuid = null;
    }
    @ModelAttribute("data")
    public DeliveryDataDTO initForm(){
        return new DeliveryDataDTO();
    }

//    @ModelAttribute("nameFirm")
//    public DeliveryFirmEnum[] firmValues(){
//        return DeliveryFirmEnum.values();
//    }
//    @ModelAttribute("address")
//    public DeliveryAddress[] addressType(){
//        return DeliveryAddress.values();
//    }


    @GetMapping("/shopping_cart")
    public ModelAndView shoppingCart(Model model){
        ShoppingCartDTO shoppingCartDTO = this.shoppingCartService.productInCart();
        List<ProductInCartDTO> productFromShoppingCart = shoppingCartDTO.getProductFromShoppingCart();
        double priceForProducts = this.shoppingCartService.calculateTotalPrice();
        this.price = priceForProducts;
        model.addAttribute("price",priceForProducts);
        return new ModelAndView("shoping_cart","products",productFromShoppingCart);
    }

    @GetMapping("/delivery")
    public ModelAndView finalDelivery(Model model) {
        List<DeliveryFirmEnum> firmName = Arrays.stream(DeliveryFirmEnum.values()).toList();
        List<DeliveryAddress> add = Arrays.stream(DeliveryAddress.values()).toList();
        model.addAttribute("nameFirm",firmName);
        model.addAttribute("address",add);
        return new ModelAndView("delivery");
    }


    @PostMapping("/delivery")
    public ModelAndView finalDelivery(DeliveryDataDTO data, @AuthenticationPrincipal UserDetails user) {
        String userEmail = user.getUsername();
        System.out.println();

        this.deliveryDataService.addAddress(data,userEmail);

        this.price = this.price + data.getPriceForDelivery();
       // model.addAttribute("price",price);
        UUID step = this.shoppingCartService.finalStep(userEmail);
        this.uuid = step;
        System.out.println();
         return new ModelAndView("redirect:/succses-delivery");
    }

    @GetMapping("/succses-delivery")
    public ModelAndView succsesDelivery (Model model) {
        model.addAttribute("price",price);
        model.addAttribute("uuid",uuid);
        return new ModelAndView("succses-delivery");
    }





}
