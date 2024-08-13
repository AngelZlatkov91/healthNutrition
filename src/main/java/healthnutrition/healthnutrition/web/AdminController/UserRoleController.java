package healthnutrition.healthnutrition.web.AdminController;
import healthnutrition.healthnutrition.models.dto.userDTOS.UserRoleDTO;
import healthnutrition.healthnutrition.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserRoleController {

    private final UserService userService;

    public UserRoleController(UserService userService) {
        this.userService = userService;
    }


    // edit role from admin
    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/admin")
    public ModelAndView editRole(Model model , @AuthenticationPrincipal UserDetails user){
        String username = user.getUsername();
        List<UserRoleDTO> userRoleDTOS = userService.usersRole(username);
        model.addAttribute("userRoleDTOS",userRoleDTOS);
        return new ModelAndView("role");
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/make-admin")
    public ModelAndView editRoleToAdmin(@RequestParam("email") String email) {
        userService.setRoleAdmin(email);
        return new ModelAndView("redirect:/edit/admin");
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/remove-admin")
    public ModelAndView editRoleToUser(@RequestParam("email") String email) {
        userService.setRoleUser(email);
        return new ModelAndView("redirect:/edit/admin");
    }

}
