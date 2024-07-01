package healthnutrition.healthnutrition.models.entitys;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "shoping_cart")
public class ShoppingCart extends BaseEntity {

    @JdbcTypeCode(Types.VARCHAR)
    private UUID deliveryNumber;
    @ManyToOne
    private User user;
    @OneToMany
    private List<ProductInCart> products;
    @Column
    private LocalDate date;
    @Column
    private Double price;

    @Column
    private boolean isGivenToDeliveriFirm;

    public ShoppingCart (){
        this.products = new ArrayList<>();
        isGivenToDeliveriFirm = false;
    }

    public UUID getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(UUID deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
        setGivenToDeliveriFirm(true);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCart> products) {
        this.products = products;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isGivenToDeliveriFirm() {
        return isGivenToDeliveriFirm;
    }

    public void setGivenToDeliveriFirm(boolean givenToDeliveriFirm) {
        isGivenToDeliveriFirm = givenToDeliveriFirm;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
