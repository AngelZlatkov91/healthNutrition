package healthnutrition.healthnutrition.models.dto.productDTOS;

import healthnutrition.healthnutrition.validation.productAndArticleValidators.UniqueBrandProduct;
import jakarta.validation.constraints.NotBlank;

public class BrandProductDTO {

    @NotBlank
    @UniqueBrandProduct
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
