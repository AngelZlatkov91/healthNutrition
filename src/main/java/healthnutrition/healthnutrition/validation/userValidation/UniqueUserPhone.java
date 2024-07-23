package healthnutrition.healthnutrition.validation.userValidation;

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
    // user phone search if exist in database
    String message() default "{register.user.phone.unique}";
    Class<?>[] groups()default {};

    Class<? extends Payload>[] payload() default {};
}
