package healthnutrition.healthnutrition.validation.DeliverValidation;

import healthnutrition.healthnutrition.validation.userValidation.PhoneNUmberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PostCodeValidator.class)
public @interface PostCode {
    String message() default "{deliver_postKode_user_correct}";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};
}
