package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.EditUserDTO;
import healthnutrition.healthnutrition.services.UserService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("editUserDTO")
    public EditUserDTO initForm(){
        return new EditUserDTO();
    }



    @GetMapping("/users/login")
    public String login(){
        return "auth-login";
    }

    @PostMapping("/users/login-error")
    public String onFailure(@ModelAttribute("email") String email, Model model) {
         model.addAttribute("email",email);
        model.addAttribute("bad_credentials","true");
        return "auth-login";
    }

    @GetMapping("/edit")
    public String edit(){
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(EditUserDTO editUserDTO, @AuthenticationPrincipal UserDetails user) {
        String userEmail = user.getUsername();
        this.userService.edit(editUserDTO,userEmail);
        return "redirect:/home";
    }








}
