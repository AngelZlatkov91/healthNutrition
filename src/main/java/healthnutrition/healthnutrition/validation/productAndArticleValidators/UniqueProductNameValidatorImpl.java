package healthnutrition.healthnutrition.validation.productAndArticleValidators;

import healthnutrition.healthnutrition.repositories.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueProductNameValidatorImpl implements ConstraintValidator<UniqueProductNameValidator,String> {
    private final ProductRepository productRepository;

    public UniqueProductNameValidatorImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return productRepository.findByName(value).isEmpty();
    }
}
