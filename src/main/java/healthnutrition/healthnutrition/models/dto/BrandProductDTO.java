package healthnutrition.healthnutrition.models.dto;

import healthnutrition.healthnutrition.validation.UniqueBrandProduct;
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
