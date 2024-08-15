package healthnutrition.healthnutrition.validation.userValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberEditValidator implements ConstraintValidator<PhoneNumberEdit,String> {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^08(1\\s?)?(\\d{1}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$");
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone.trim().isEmpty()){
            return true;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
