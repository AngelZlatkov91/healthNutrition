package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.GetTypesDTO;
import healthnutrition.healthnutrition.models.dto.TypeProductDTO;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import healthnutrition.healthnutrition.services.TypeProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeProductServiceImpl implements TypeProductService {
    private final TypeRepository typeRepository;

    public TypeProductServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void addType(TypeProductDTO typeProductDTO) {
        TypeProduct type = new TypeProduct();
        type.setType(typeProductDTO.getType());
        this.typeRepository.save(type);
    }

    @Override
    public List<GetTypesDTO> allTypes() {
        return this.typeRepository.findAll().stream().map(type-> new GetTypesDTO(type.getType())).collect(Collectors.toList());
    }
}