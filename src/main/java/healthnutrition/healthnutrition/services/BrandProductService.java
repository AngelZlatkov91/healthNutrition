package healthnutrition.healthnutrition.services;

import healthnutrition.healthnutrition.models.dto.BrandProductDTO;
import healthnutrition.healthnutrition.models.dto.GetBrandsDTO;

import java.util.List;

public interface BrandProductService {

    void addBrand(BrandProductDTO brandProductDTO);
    List<GetBrandsDTO> allBrands();
}
