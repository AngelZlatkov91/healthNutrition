package healthnutrition.healthnutrition.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shoping_cart")
public class ShoppingCart extends BaseEntity {

    @Column(unique = true)
    private String deliveryNumber;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Product> products;






}
