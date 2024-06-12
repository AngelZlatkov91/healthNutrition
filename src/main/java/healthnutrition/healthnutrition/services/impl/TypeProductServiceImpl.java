package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.GetTypesDTO;
import healthnutrition.healthnutrition.models.dto.TypeProductDTO;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import healthnutrition.healthnutrition.services.TypeProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeProductServiceImpl implements TypeProductService {
    private final TypeRepository typeRepository;
    private final ModelMapper mapper;

    public TypeProductServiceImpl(TypeRepository typeRepository, ModelMapper mapper) {
        this.typeRepository = typeRepository;
        this.mapper = mapper;
    }

    @Override
    public void addType(TypeProductDTO typeProductDTO) {
        this.typeRepository.save(this.mapper.map(typeProductDTO,TypeProduct.class));
    }

    @Override
    public List<GetTypesDTO> allTypes() {
        return this.typeRepository.findAll().stream().map(type-> new GetTypesDTO(type.getType())).collect(Collectors.toList());
    }
}
