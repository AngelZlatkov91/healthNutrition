package healthnutrition.healthnutrition.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueTypeProductValidator.class)
public @interface UniqueTypeProduct {


    String message() default "The type should be unique";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};

}
