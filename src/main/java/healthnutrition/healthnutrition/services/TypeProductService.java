package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.productDTOS.GetTypesDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.TypeProductDTO;

import java.util.List;

public interface TypeProductService {
    void addType(TypeProductDTO typeProductDTO);

    List<GetTypesDTO> allTypes();


}
