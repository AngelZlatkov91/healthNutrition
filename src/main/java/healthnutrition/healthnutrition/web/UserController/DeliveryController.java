package healthnutrition.healthnutrition.web.UserController;

import healthnutrition.healthnutrition.models.dto.cartDTOS.DeliveryDataDTO;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DeliveryController {
    private final ShoppingCartService shoppingCartService;

    public DeliveryController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
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
    @GetMapping("/delivery")
    public ModelAndView finalDelivery() {
        return new ModelAndView("delivery");
    }
    // view for delivery data to send shopping cart and final step for order
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
        this.shoppingCartService.finalStep(userEmail,data);
        return  new ModelAndView("redirect:/succses-delivery");
    }

    @GetMapping("/succses-delivery")
    public ModelAndView successesDelivery() {
        return new ModelAndView("succses-delivery");
    }
}
