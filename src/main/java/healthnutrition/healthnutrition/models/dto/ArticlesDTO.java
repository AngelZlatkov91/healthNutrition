package healthnutrition.healthnutrition.models.dto;

import healthnutrition.healthnutrition.validation.UniqueTitleArticle;
import jakarta.validation.constraints.NotBlank;

public class ArticlesDTO {
    private String id;
    @NotBlank
    @UniqueTitleArticle
    private String title;
    @NotBlank
    private String description;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
