package healthnutrition.healthnutrition.models.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "types_products")
public class TypeProduct extends BaseEntity{
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
