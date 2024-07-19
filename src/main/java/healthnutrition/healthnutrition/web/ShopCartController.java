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

    private double price;
    private UUID uuid;
    private  int size;

    public ShopCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;

        this.price = 0.0;
        this.uuid = null;
        this.size = 0;
    }
    @ModelAttribute("data")
    public DeliveryDataDTO initForm(){
        return new DeliveryDataDTO();
    }

    @ModelAttribute("nameFirm")
    public DeliveryFirmEnum[] firmValues(){
        return DeliveryFirmEnum.values();
    }
    @ModelAttribute("address")
    public DeliveryAddress[] addressType(){
        return DeliveryAddress.values();
    }


    @GetMapping("/shopping_cart")
    public ModelAndView shoppingCart(Model model){
        ShoppingCartDTO shoppingCartDTO = this.shoppingCartService.productInCart();
        List<ProductInCartDTO> productFromShoppingCart = shoppingCartDTO.getProductFromShoppingCart();
        double priceForProducts = this.shoppingCartService.calculateTotalPrice();
        int size = productFromShoppingCart.size();
        this.size = size;
        this.price = priceForProducts;
        model.addAttribute("price",priceForProducts);
        model.addAttribute("size",size);
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

    @GetMapping("/delivery")
    public ModelAndView finalDelivery(Model model) {
        if (this.size == 0) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("delivery");
    }

    @PostMapping("/delivery")
    public ModelAndView finalDelivery(@Valid DeliveryDataDTO data,
                                      BindingResult bindingResult,
                                       RedirectAttributes rAtt,
                                @AuthenticationPrincipal UserDetails user) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.data",bindingResult);
            rAtt.addFlashAttribute("data",data);
          return new ModelAndView ("redirect:/delivery");
        }
        String userEmail = user.getUsername();
        data.add();
        this.price = this.price + data.getPriceForDelivery();
       // model.addAttribute("price",price);
        UUID step = this.shoppingCartService.finalStep(userEmail,data);
        this.uuid = step;
         return  new ModelAndView("redirect:/succses-delivery");
    }

    @GetMapping("/succses-delivery")
    public ModelAndView succsesDelivery (Model model) {
        if (this.size == 0) {
            return new ModelAndView("redirect:/home");
        }
        model.addAttribute("price",price);
        model.addAttribute("uuid",uuid);
        return new ModelAndView("succses-delivery");
    }





}
