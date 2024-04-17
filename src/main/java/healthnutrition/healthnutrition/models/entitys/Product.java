package healthnutrition.healthnutrition.models.entitys;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int availability;
    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    private TypeProduct type;
    @ManyToOne
    private BrandProduct brant;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public TypeProduct getType() {
        return type;
    }

    public void setType(TypeProduct type) {
        this.type = type;
    }

    public BrandProduct getBrant() {
        return brant;
    }

    public void setBrant(BrandProduct brant) {
        this.brant = brant;
    }
}
