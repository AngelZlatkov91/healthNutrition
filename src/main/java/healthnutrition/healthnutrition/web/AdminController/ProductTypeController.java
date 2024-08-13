package healthnutrition.healthnutrition.web.AdminController;

import healthnutrition.healthnutrition.models.dto.productDTOS.TypeProductDTO;
import healthnutrition.healthnutrition.services.TypeProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductTypeController {
    private final TypeProductService type;

    public ProductTypeController(TypeProductService type) {
        this.type = type;
    }

    @ModelAttribute("typeProductDTO")
    public TypeProductDTO initForm(){
        return new TypeProductDTO();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/add/type")
    public ModelAndView addType(){
        return new ModelAndView("type-add");
    }

    // adding product type from admin
    @Secured("ROLE_ADMIN")
    @PostMapping("/add/type")
    public ModelAndView addType(@Valid TypeProductDTO typeProductDTO,
                          BindingResult bindingResult,
                          RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("typeProductDTO",typeProductDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.typeProductDTO",bindingResult);
            return new ModelAndView("redirect:/add/type");
        }
        this.type.addType(typeProductDTO);
        return new ModelAndView( "redirect:/add/type");
    }
}
