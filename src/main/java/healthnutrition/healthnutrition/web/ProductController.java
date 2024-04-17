package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.dto.ProductInCartDTO;
import healthnutrition.healthnutrition.services.BrandProductService;
import healthnutrition.healthnutrition.services.ProductService;
import healthnutrition.healthnutrition.services.TypeProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
   private final ProductService productService;
   private final BrandProductService brand;
   private final TypeProductService type;
   private final List<ProductInCartDTO> product;

    public ProductController(ProductService productService, BrandProductService brand, TypeProductService typeProductService) {
        this.productService = productService;
        this.brand = brand;
        this.type = typeProductService;
        this.product = new ArrayList<>();
    }



    @GetMapping("/product-add")
    public String add(Model model) {
        if (!model.containsAttribute("productCreateDTO")) {
            model.addAttribute("productCreateDTO", ProductCreateDTO.empty());
        }
        model.addAttribute("brands",brand.allBrands());
        model.addAttribute("types",type.allTypes());
        return "product-add";
    }


    @PostMapping("/product-add")
    public String add(@Valid ProductCreateDTO productCreateDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("productCreateDTO",productCreateDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.productCreateDTO",bindingResult);
            return "redirect:/product-add";
        }
        this.productService.addProduct(productCreateDTO);
        return "redirect:/";
    }
    @GetMapping("/product/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model) {
        ProductDetailsDTO productDetailsDTO = productService.getProductDetails(uuid);
        model.addAttribute("product",productDetailsDTO);
        return "details";
    }
    @PostMapping("/product/{uuid}")
    public String addProductToCart(@PathVariable("uuid") UUID uuid, Model model) {
        ProductDetailsDTO productDetails = productService.getProductDetails(uuid);
        model.addAttribute("product",productDetails);
        ProductInCartDTO productInCartDTO = new ProductInCartDTO();
        productInCartDTO.setName(productDetails.getName());
        productInCartDTO.setPrice(productDetails.getPrice());
        productInCartDTO.setQuantity(1);
        product.add(productInCartDTO);
        return "products";
    }

}
