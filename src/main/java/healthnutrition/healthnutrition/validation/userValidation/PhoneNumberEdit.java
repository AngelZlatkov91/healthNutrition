package healthnutrition.healthnutrition.validation.userValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PhoneNumberEditValidator.class)
public @interface PhoneNumberEdit {
    String message() default "{register.user.phone.number}";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};
}
