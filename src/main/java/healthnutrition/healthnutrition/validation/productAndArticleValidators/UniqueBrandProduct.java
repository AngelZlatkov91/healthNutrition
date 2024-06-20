package healthnutrition.healthnutrition.validation.productAndArticleValidators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueBrandProductValidator.class)
public @interface UniqueBrandProduct {
    String message() default "The brand should be unique";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};

}
