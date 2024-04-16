package healthnutrition.healthnutrition.models.entitys;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "types_products")
public class TypeProduct extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
