package healthnutrition.healthnutrition.web;

import healthnutrition.healthnutrition.models.dto.ProductDetailsDTO;
import healthnutrition.healthnutrition.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/all")
    public String all(Model model, Pageable pageable){
        Page<ProductDetailsDTO> allProducts = productService.getAllProducts(pageable);
        model.addAttribute("products",allProducts);
        return "products";

    }
}
