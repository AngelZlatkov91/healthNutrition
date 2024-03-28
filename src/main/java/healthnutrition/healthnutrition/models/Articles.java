package healthnutrition.healthnutrition.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "articles")
public class Articles extends BaseEntity {
    private String title;

    private String description;

    private String imageUrl;

}
