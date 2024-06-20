package healthnutrition.healthnutrition.web;
import healthnutrition.healthnutrition.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login(){
        return "auth-login";
    }

    @PostMapping("/login-error")
    public String onFailure(@ModelAttribute("email") String email, Model model) {
         model.addAttribute("email",email);
        model.addAttribute("bad_credentials","true");
        return "auth-login";
    }










}
