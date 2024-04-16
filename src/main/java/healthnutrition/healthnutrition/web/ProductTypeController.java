package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.TypeProductDTO;
import healthnutrition.healthnutrition.services.TypeProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/add/type")
    public String addType(){
        return "type-add";
    }
    @PostMapping("/add/type")
    public String addType(@Valid TypeProductDTO typeProductDTO,
                          BindingResult bindingResult,
                          RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("typeProductDTO",typeProductDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.typeProductDTO",bindingResult);
            return "redirect:/add/type";
        }
        this.type.addType(typeProductDTO);
        return "redirect:/";
    }
}
