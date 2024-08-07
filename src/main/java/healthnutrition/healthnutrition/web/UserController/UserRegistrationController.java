package healthnutrition.healthnutrition.web.UserController;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRegisterDTo;
import healthnutrition.healthnutrition.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {
    private final UserService userService;
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("userRegistrationDTO")
    public UserRegisterDTo initForm(){
        return new UserRegisterDTo();
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView ("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterDTo userRegistrationDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO",bindingResult);
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            return new ModelAndView("redirect:/users/register");
        }
        this.userService.registerUser(userRegistrationDTO);
     return new ModelAndView("redirect:/login");
    }
}
