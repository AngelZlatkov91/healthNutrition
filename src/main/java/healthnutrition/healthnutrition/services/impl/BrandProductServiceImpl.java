package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.productDTOS.BrandProductDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.GetBrandsDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.services.BrandProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandProductServiceImpl implements BrandProductService {
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    public BrandProductServiceImpl(BrandRepository brandRepository, ModelMapper mapper) {
        this.brandRepository = brandRepository;
        this.mapper = mapper;
    }
    @Override
    public void addBrand(BrandProductDTO brandProductDTO) {
        this.brandRepository.save(this.mapper.map(brandProductDTO,BrandProduct.class));
    }
    @Override
    public List<GetBrandsDTO> allBrands() {
        return this.brandRepository.findAll().stream()
                .map(brand -> new GetBrandsDTO(brand.getBrand(), brand.getImageUrl()))
                .collect(Collectors.toList());
    }
}
