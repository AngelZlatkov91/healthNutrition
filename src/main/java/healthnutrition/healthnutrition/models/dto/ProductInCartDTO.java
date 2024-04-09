package healthnutrition.healthnutrition.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductInCartDTO {

    private String name;

    private Double price;

    private int quantity;

    private Double totalPricePerProduct;

    public ProductInCartDTO () {

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPricePerProduct() {
        return totalPricePerProduct;
    }


    public Double totalPrice () {
        return this.quantity * this.price;
    }
}
