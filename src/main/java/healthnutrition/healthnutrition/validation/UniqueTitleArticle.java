package healthnutrition.healthnutrition.validation;

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

    String message() default "The title for this Article should be unique";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};
}
