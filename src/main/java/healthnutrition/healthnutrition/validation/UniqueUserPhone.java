package healthnutrition.healthnutrition.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueUserPhoneValidator.class)
public @interface UniqueUserPhone {
    String message() default "The phone all ready exist";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};
}
