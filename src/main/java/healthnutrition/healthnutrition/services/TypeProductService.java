package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.GetTypesDTO;
import healthnutrition.healthnutrition.models.dto.TypeProductDTO;

import java.util.List;

public interface TypeProductService {
    void addType(TypeProductDTO typeProductDTO);

    List<GetTypesDTO> allTypes();


}
