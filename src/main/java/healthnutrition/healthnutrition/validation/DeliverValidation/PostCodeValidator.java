package healthnutrition.healthnutrition.validation.DeliverValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PostCodeValidator implements ConstraintValidator<PostCode,String> {

    private static final Pattern POST_CODE_PATTERN = Pattern.compile("^[0-9]{4}$");

    @Override
    public boolean isValid(String postCode, ConstraintValidatorContext constraintValidatorContext) {
        if (postCode.isEmpty()){
            return false;
        }
        return POST_CODE_PATTERN.matcher(postCode).matches();
    }

}
