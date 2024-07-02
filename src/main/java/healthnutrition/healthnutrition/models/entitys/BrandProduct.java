package healthnutrition.healthnutrition.models.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands_products")
public class BrandProduct extends BaseEntity{
     @Column(nullable = false,unique = true)
    private String brand;

     @Column(columnDefinition = "LONGTEXT")
     private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
