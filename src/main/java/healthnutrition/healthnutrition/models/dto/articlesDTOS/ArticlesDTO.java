package healthnutrition.healthnutrition.models.dto.articlesDTOS;

import healthnutrition.healthnutrition.validation.productAndArticleValidators.UniqueTitleArticle;
import jakarta.validation.constraints.NotBlank;

public class ArticlesDTO {
    private String id;
    @NotBlank(message = "{article.title.empty}")
    @UniqueTitleArticle
    private String title;
    @NotBlank(message = "{article.description}")
    private String description;


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


}
