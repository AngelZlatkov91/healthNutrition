package healthnutrition.healthnutrition.models.dto.productDTOS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductEditPrice {
    @NotBlank(message = "{product.name.empty}")
    private String name;
    @Positive(message = "{product.price.positive}")
    private Double price;

    public ProductEditPrice() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
