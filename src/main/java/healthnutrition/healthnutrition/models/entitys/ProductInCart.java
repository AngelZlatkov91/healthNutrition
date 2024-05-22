package healthnutrition.healthnutrition.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_in_cart")
public class ProductInCart extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double singlePrice;

    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
