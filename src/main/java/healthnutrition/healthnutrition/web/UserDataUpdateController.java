package healthnutrition.healthnutrition.web;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ArchiveDTO;
import healthnutrition.healthnutrition.models.dto.userDTOS.EditUserDTO;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserUpdateDTO;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import healthnutrition.healthnutrition.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserDataUpdateController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public UserDataUpdateController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @ModelAttribute("editUserDTO")
    public EditUserDTO initForm(){
        return new EditUserDTO();
    }

    @GetMapping("/profile")
    public ModelAndView updateProfile(@AuthenticationPrincipal UserDetails user, Model model) {
        String userName = user.getUsername();
        UserUpdateDTO userData  = this.userService.getUserData(userName);
        ArchiveDTO archiveShoppingCartDTOS = this.shoppingCartService.allShoppingCarts(userName);
        model.addAttribute("archive",archiveShoppingCartDTOS);
        model.addAttribute("user", userData);
        return new ModelAndView("profile");
    }

    @GetMapping("/edit")
    public ModelAndView edit(){
        return new ModelAndView("edit");
    }

    @PostMapping("/edit")
    public ModelAndView edit(@Valid EditUserDTO editUserDTO, BindingResult br
            , RedirectAttributes rat, @AuthenticationPrincipal UserDetails user) {
        if (br.hasErrors()) {
            rat.addFlashAttribute("org.springframework.validation.BindingResult.editUserDTO",br);

            rat.addFlashAttribute("editUserDTO",editUserDTO);
            return new ModelAndView("redirect:/edit");
        }
        String userEmail = user.getUsername();
        this.userService.edit(editUserDTO,userEmail);
        return new ModelAndView("redirect:/home");
    }


}
