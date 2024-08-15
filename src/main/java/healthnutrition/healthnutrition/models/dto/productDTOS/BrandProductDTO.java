package healthnutrition.healthnutrition.models.dto.productDTOS;

import healthnutrition.healthnutrition.validation.productAndArticleValidators.UniqueBrandProduct;
import jakarta.validation.constraints.NotBlank;

public class BrandProductDTO {

    @NotBlank(message = "{brand.product.empty}")
    @UniqueBrandProduct
    private String brand;

    @NotBlank(message = "{brand.image.empty}")
    private String imageUrl;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
