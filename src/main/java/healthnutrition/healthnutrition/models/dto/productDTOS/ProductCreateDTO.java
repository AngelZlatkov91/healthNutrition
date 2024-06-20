package healthnutrition.healthnutrition.models.dto.productDTOS;

import healthnutrition.healthnutrition.validation.productAndArticleValidators.UniqueProductNameValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ProductCreateDTO  {
    @Size(min = 3)
    @NotBlank
    @UniqueProductNameValidator
    private String name;
    @NotBlank
    private String description;
    @Positive
    private Double price;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private String type;
    @NotBlank
    private String brand;

    public ProductCreateDTO(){

    }

    public ProductCreateDTO(String name, String description, Double price, String imageUrl, String type, String brand) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.type = type;
        this.brand = brand;
    }

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public static ProductCreateDTO empty() {
       return new ProductCreateDTO( null,null,null,null,null,null);
    }


}
