package healthnutrition.healthnutrition.models.dto;

import healthnutrition.healthnutrition.validation.UniqueProductNameValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.apache.kafka.common.protocol.types.Field;

public class ProductCreateDTO {
    @Size(min = 3)
    @NotBlank
    @UniqueProductNameValidator
    private String name;
    @NotBlank
    private String description;
    @Positive
    private Double price;
    @Positive
    private int availability;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private String type;
    @NotBlank
    private String brant;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrant() {
        return brant;
    }

    public void setBrant(String brant) {
        this.brant = brant;
    }
}
