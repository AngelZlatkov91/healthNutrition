package healthnutrition.healthnutrition.models.dto;

import healthnutrition.healthnutrition.validation.UniqueTypeProduct;

import jakarta.validation.constraints.NotBlank;

public class TypeProductDTO {
   @NotBlank
     @UniqueTypeProduct
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
