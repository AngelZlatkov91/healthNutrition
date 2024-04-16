package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.BrandProductDTO;
import healthnutrition.healthnutrition.models.dto.GetBrandsDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.services.BrandProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandProductServiceImpl implements BrandProductService {
    private final BrandRepository brandRepository;

    public BrandProductServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void addBrand(BrandProductDTO brandProductDTO) {
        BrandProduct brand = new BrandProduct();
        brand.setBrand(brandProductDTO.getBrand());
        this.brandRepository.save(brand);
    }

    @Override
    public List<GetBrandsDTO> allBrands() {
        return this.brandRepository.findAll().stream()
                .map(brand -> new GetBrandsDTO(brand.getBrand()))
                .collect(Collectors.toList());
    }

}
