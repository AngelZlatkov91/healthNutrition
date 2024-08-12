package healthnutrition.healthnutrition.web.RestControler;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.RestProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductRest {
    private final RestProductService restProductService;

    public ProductRest(RestProductService restProductService) {
        this.restProductService = restProductService;
    }

    @GetMapping("/rest/get/products")
    public ModelAndView getAllProducts(Model model){
        List<ProductDetailsDTO> allProducts =
                restProductService.getAllProducts();
        model.addAttribute("allProducts",allProducts);
        return new ModelAndView("redirect:/home");
    }
    @GetMapping("/rest/get/products/{id}")
    public ModelAndView getProductById(@PathVariable("id") Long id, Model model) {
        ProductDetailsDTO productById = restProductService.getProductById(id);
        model.addAttribute("product",productById);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/rest/remove/products/{id}")
    public ModelAndView removeProductById(@PathVariable("id") Long id) {
        restProductService.deleteProduct(id);
        return new ModelAndView("redirect:/home");
    }

    @ModelAttribute("productCreateDTO")
    public ProductCreateDTO initForm(){
        return new ProductCreateDTO();
    }
//    @GetMapping("/rest/create/product")
//    public ModelAndView createProduct(Model model) {
//        return new ModelAndView("redirect:/home");
//    }

    @PostMapping("/rest/create/product")
    public ModelAndView createProduct(ProductCreateDTO productCreateDTO, Model model) {

        Long l = restProductService.addProduct(productCreateDTO);
         model.addAttribute("id",l);
        return new ModelAndView("redirect:/home");
    }

}
