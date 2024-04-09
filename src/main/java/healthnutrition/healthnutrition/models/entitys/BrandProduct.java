package healthnutrition.healthnutrition.models.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands_products")
public class BrandProduct extends BaseEntity{

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
