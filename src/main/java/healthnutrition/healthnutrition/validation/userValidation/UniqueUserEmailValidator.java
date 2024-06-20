package healthnutrition.healthnutrition.validation.userValidation;

import healthnutrition.healthnutrition.repositories.UserRepositories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private final UserRepositories userRepository;

    public UniqueUserEmailValidator(UserRepositories userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return userRepository.findByEmail(value).isEmpty();
    }
}
