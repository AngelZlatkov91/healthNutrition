package healthnutrition.healthnutrition.validation.userValidation;

import healthnutrition.healthnutrition.repositories.UserRepositories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniqueUserPhoneValidator implements ConstraintValidator<UniqueUserPhone,String> {

private final UserRepositories userRepositories;

    public UniqueUserPhoneValidator(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepositories.findByPhone(value).isEmpty();
    }
}
