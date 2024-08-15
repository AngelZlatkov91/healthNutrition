package healthnutrition.healthnutrition.validation.productAndArticleValidators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueTitleArticleValidator.class)
public @interface UniqueTitleArticle {
    // title for the new article search if exist in database
    String message() default "{article.title.unique}";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};
}
