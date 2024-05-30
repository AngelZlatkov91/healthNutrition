package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.BrandProductDTO;
import healthnutrition.healthnutrition.services.BrandProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductBrandController {
    private final BrandProductService brandProductService;

    public ProductBrandController(BrandProductService brandProductService) {
        this.brandProductService = brandProductService;
    }

    @ModelAttribute("brandProductDTO")
    public BrandProductDTO initForm(){
        return new BrandProductDTO();
    }
    @GetMapping("/add/brand")
    public String addBrand() {
        return "brand-add";
    }

    @PostMapping("/add/brand")
    public String addBrand(@Valid BrandProductDTO brandProductDTO,
                           BindingResult bindingResult,
                           RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("brandProductDTO",brandProductDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.brandProductDTO",bindingResult);
               return "redirect:/add/brand";
        }
        this.brandProductService.addBrand(brandProductDTO);
        return "redirect:/add/brand";
    }
}
