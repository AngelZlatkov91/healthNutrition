package healthnutrition.healthnutrition.models.dto;

import healthnutrition.healthnutrition.validation.UniqueProductNameValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record ProductCreateDTO (@Size(min = 3) @NotBlank @UniqueProductNameValidator String name,
                                @NotBlank String description,
                                @Positive Double price,
                                @NotBlank String imageUrl,
                                @NotBlank String type,
                                @NotBlank String brand) {


    public static ProductCreateDTO empty() {
       return new ProductCreateDTO(null,null,null,null,null,null);
    }


}
