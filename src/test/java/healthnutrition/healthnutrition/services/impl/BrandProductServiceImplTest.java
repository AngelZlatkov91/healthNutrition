package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.productDTOS.BrandProductDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.GetBrandsDTO;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BrandProductServiceImplTest {

     @Autowired
    private BrandProductServiceImpl brandProductService;
     @Autowired
    private BrandRepository brandRepository;

     @BeforeEach
    void setUp(){
         brandRepository.deleteAll();
     }

     @AfterEach
    void cleanUp(){
         brandRepository.deleteAll();
     }

     @Test
    public void addBrand(){
         brandProductService.addBrand(brandProductDTO());
         assertEquals(1,brandRepository.count());
     }
    @Test
    public void addTypeExistingType(){
        brandProductService.addBrand(brandProductDTO());
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            brandProductService.addBrand(brandProductDTO());
        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void addTypeThrowWithTypeData(){
        BrandProductDTO brandProductDTO = new BrandProductDTO();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            brandProductService.addBrand(brandProductDTO);
        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void getAllBrands(){
        BrandProductDTO brandProductDTO = brandProductDTO();
        brandProductService.addBrand(brandProductDTO);
        List<GetBrandsDTO> getBrandsDTOS = brandProductService.allBrands();
        GetBrandsDTO getBrandsDTO = getBrandsDTOS.get(0);
        assertEquals(getBrandsDTO.brand(),brandProductDTO.getBrand());
    }



     private BrandProductDTO brandProductDTO(){
         BrandProductDTO brand = new BrandProductDTO();
         brand.setBrand("Amix");
         brand.setImageUrl("Amix image url");
         return brand;
     }
}