package healthnutrition.healthnutrition.validation.productAndArticleValidators;

import healthnutrition.healthnutrition.repositories.BrandRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueBrandProductValidator implements ConstraintValidator<UniqueBrandProduct, String> {


    private final BrandRepository brandRepository;

    public UniqueBrandProductValidator(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.brandRepository.findByBrand(s).isEmpty();
    }
}
