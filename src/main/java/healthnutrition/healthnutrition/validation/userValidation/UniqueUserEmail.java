package healthnutrition.healthnutrition.validation.userValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueUserEmailValidator.class)
public @interface UniqueUserEmail {
    // user email search if exist in database
    String message() default "{register.user.email.unique}";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};
}
