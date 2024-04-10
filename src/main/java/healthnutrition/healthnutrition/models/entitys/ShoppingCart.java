package healthnutrition.healthnutrition.models.entitys;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shoping_cart")
public class ShoppingCart extends BaseEntity {

    @JdbcTypeCode(Types.INTEGER)
    private UUID deliveryNumber;
    @ManyToOne
    private UserEntity user;
    @OneToMany
    private List<Product> products;
    @Column
    private LocalDate date;

    @Column
    private boolean isDelivered;

    public ShoppingCart (){
        this.products = new ArrayList<>();
    }

    public UUID getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(UUID deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
