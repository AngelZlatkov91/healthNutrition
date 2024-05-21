package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.UserUpdateDTO;
import healthnutrition.healthnutrition.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserDataUpdateController {

    private final UserService userService;

    public UserDataUpdateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView updateProfile(@AuthenticationPrincipal UserDetails user, Model model) {
        String userName = user.getUsername();
        UserUpdateDTO userData  = this.userService.getUserData(userName);
        model.addAttribute("user", userData);
        return new ModelAndView("profile");
    }
}
