package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.productDTOS.GetTypesDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.TypeProductDTO;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TypeProductServiceImplTest {

    @Autowired
    private TypeProductServiceImpl typeProductService;

    @Autowired
    private TypeRepository typeRepository;

    @BeforeEach
    void setUp(){
        typeRepository.deleteAll();
    }
    @AfterEach
    void cleanUp(){
        typeRepository.deleteAll();
    }


    @Test
    public void addType(){
        typeProductService.addType(typeProductDTO());
        assertEquals(1,typeRepository.count());
    }
    @Test
    public void addTypeExistingType(){
        typeProductService.addType(typeProductDTO());
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            typeProductService.addType(typeProductDTO());
        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public  void addTypeThrowWithData(){
        TypeProductDTO typeProductDTO1 = new TypeProductDTO();
        typeProductDTO1.setType(null);
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            typeProductService.addType(typeProductDTO1);
        });
        String expectedMessage = "could not execute statement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void getAllTypes(){
        TypeProductDTO typeProductDTO = typeProductDTO();
        typeProductService.addType(typeProductDTO());
        List<GetTypesDTO> getTypesDTOS = typeProductService.allTypes();
        GetTypesDTO getTypesDTO = getTypesDTOS.get(0);
        assertEquals(getTypesDTO.type(),typeProductDTO.getType());


    }

    private TypeProductDTO typeProductDTO(){
        TypeProductDTO typeProductDTO = new TypeProductDTO();
        typeProductDTO.setType("Protein");
        return typeProductDTO;
    }

}