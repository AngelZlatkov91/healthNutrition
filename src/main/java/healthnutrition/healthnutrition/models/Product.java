package healthnutrition.healthnutrition.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false, columnDefinition = "VERY LONG TEXT")
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private int count;
    @Column(nullable = false)
    private String imageUrl;

}
