package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductDetailsDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.BrandRepository;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.TypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestProductServiceImplTest {
    @Autowired
    private RestProductServiceImpl restProductService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TypeRepository typeRepository;

    @BeforeEach
    void setUp(){
        productRepository.deleteAll();
        brandRepository.deleteAll();
        typeRepository.deleteAll();
    }

    @AfterEach
    void cleanUp(){
        productRepository.deleteAll();
        brandRepository.deleteAll();
        typeRepository.deleteAll();
    }
    @Test
    public void testAddProduct(){
        brandRepository.save(brand());
        typeRepository.save(type());
        Long id = restProductService.addProduct(createProduct());
        assertEquals(1,productRepository.count());

    }
    @Test
    public void testAddSameProduct(){
        brandRepository.save(brand());
        typeRepository.save(type());
        restProductService.addProduct(createProduct());
        assertThrows(DataIntegrityViolationException.class, () -> {
            restProductService.addProduct(createProduct());
        });
    }
    @Test
    public void testAddEmptyProduct(){
        ProductCreateDTO productCreateDTO = new ProductCreateDTO();
        assertThrows(NoSuchElementException.class, () -> {
            restProductService.addProduct(productCreateDTO);
        });
    }
    @Test
    public void testGetProductDetails(){
        brandRepository.save(brand());
        typeRepository.save(type());
        restProductService.addProduct(createProduct());
        Optional<Product> byName =
                productRepository.findByName("fat burner");
        Optional<ProductDetailsDTO> productDetails = restProductService.getProductById(byName.get().getUuid());
        assertEquals(productDetails.get().getName(),createProduct().getName());
        assertEquals(byName.get().getName(),productDetails.get().getName());
    }
    @Test
    public void testDeleteExistingProductByUUID(){
        brandRepository.save(brand());
        typeRepository.save(type());
        restProductService.addProduct(createProduct());
        Optional<Product> byName =
                productRepository.findByName("fat burner");
        restProductService.deleteProduct(byName.get().getUuid());
        assertEquals(0,productRepository.count());
    }
    @Test
    public void testDeleteNotExistingProduct(){
        UUID uuid = UUID.randomUUID();
        brandRepository.save(brand());
        typeRepository.save(type());
        restProductService.addProduct(createProduct());

        restProductService.deleteProduct(uuid);
        assertEquals(1,productRepository.count());

    }

    @Test
    public void testGetAllProducts(){
        List<ProductDetailsDTO> allProducts =
                restProductService.getAllProducts();
        assertTrue(allProducts.isEmpty());
    }




    private ProductCreateDTO createProduct(){
        return new ProductCreateDTO("fat burner","fatBurning",50.00,"imageURl","FAT","AMIX");
    }

    private BrandProduct brand(){
        BrandProduct brandProduct = new BrandProduct();
        brandProduct.setBrand("AMIX");
        brandProduct.setImageUrl("picture");
        return brandProduct;
    }

    private TypeProduct type(){
        TypeProduct typeProduct = new TypeProduct();
        typeProduct.setType("FAT");
        return typeProduct;
    }
}