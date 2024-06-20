package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.productDTOS.BrandProductDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.GetBrandsDTO;

import java.util.List;

public interface BrandProductService {

    void addBrand(BrandProductDTO brandProductDTO);
    List<GetBrandsDTO> allBrands();
}
