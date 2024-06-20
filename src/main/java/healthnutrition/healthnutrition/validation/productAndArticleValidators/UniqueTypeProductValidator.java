package healthnutrition.healthnutrition.validation.productAndArticleValidators;

import healthnutrition.healthnutrition.repositories.TypeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueTypeProductValidator implements ConstraintValidator<UniqueTypeProduct,String> {

    private final TypeRepository typeRepository;

    public UniqueTypeProductValidator(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.typeRepository.findByType(s).isEmpty();
    }
}
