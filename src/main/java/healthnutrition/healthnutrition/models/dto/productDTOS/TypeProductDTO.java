package healthnutrition.healthnutrition.models.dto.productDTOS;

import healthnutrition.healthnutrition.validation.productAndArticleValidators.UniqueTypeProduct;

import jakarta.validation.constraints.NotBlank;

public class TypeProductDTO {
   @NotBlank(message = "{type.product.empty}")
   @UniqueTypeProduct
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
