package healthnutrition.healthnutrition.validation;

import healthnutrition.healthnutrition.repositories.ArticlesRepositories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueTitleArticleValidator implements ConstraintValidator<UniqueTitleArticle, String> {

   private final ArticlesRepositories articlesRepositories;

    public UniqueTitleArticleValidator(ArticlesRepositories articlesRepositories) {
        this.articlesRepositories = articlesRepositories;
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return articlesRepositories.findByTitle(s).isEmpty();
    }
}
