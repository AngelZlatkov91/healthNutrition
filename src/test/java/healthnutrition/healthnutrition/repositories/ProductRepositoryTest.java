package healthnutrition.healthnutrition.repositories;

import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;
    @Autowired
    TypeRepository typeRepository;

    private Product product;
    private TypeProduct type;
    private BrandProduct brand;
    private UUID uuid;

    @BeforeEach
    public void setUp(){
        type = new TypeProduct();
        type.setType("Protein");
        brand = new BrandProduct();
        brand.setBrand("Amix");
        brand.setImageUrl("isolate amix protein");
        product = new Product();
        product.setName("isolate");
        product.setPrice(50.00);
        product.setUuid(UUID.randomUUID());
        product.setType(type);
        product.setBrant(brand);
        product.setImageUrl("koutia");
        product.setDescription("product test");
        uuid = product.getUuid();
        productRepository.save(product);
        typeRepository.save(type);
        brandRepository.save(brand);
    }

    @AfterEach
    public  void tearDow() {
        productRepository.delete(product);
        brandRepository.delete(brand);
        typeRepository.delete(type);
    }

    @Test
    public void testAddSameProduct(){

            productRepository.save(product);
       assertEquals(1,productRepository.count());

    }

    @Test
    public void testFindByName(){
        Optional<Product> byName = productRepository.findByName("isolate");
        assertEquals(byName.get().getName(),"isolate");
    }

    @Test
    public void testFindByUUID(){
        Product byUuid = productRepository.findByUuid(uuid);
        assertEquals(byUuid.getName(),"isolate");
    }
    @Test
    public void testNotExistProduct(){
        Optional<Product> byName = productRepository.findByName("fat burner");
        assertTrue(byName.isEmpty());
    }

    @Test
    public void testDeleteByUUID(){
        productRepository.deleteByUuid(uuid);
        assertEquals(0,productRepository.count());
    }

    @Test
    public void testSearchKyeByBrandCorrectProduct(){
        String key = "amix";
        Pageable pageable = Pageable.unpaged();
        List<Product> byKey = productRepository.findByNameContainingIgnoreCaseOrBrantBrandContainingIgnoreCaseOrTypeTypeContainingIgnoreCase(key, key, key, pageable);
        assertEquals(1,byKey.size());
    }
    @Test
    public void testSearchKyeByNameCorrectProduct(){
        String key = "ISOLATE";
        Pageable pageable = Pageable.unpaged();
        List<Product> byKey = productRepository.findByNameContainingIgnoreCaseOrBrantBrandContainingIgnoreCaseOrTypeTypeContainingIgnoreCase(key, key, key, pageable);
        assertEquals(1,byKey.size());
    }
    @Test
    public void testSearchKyeByTypeCorrectProduct(){
        String key = "PROTEIN";
        Pageable pageable = Pageable.unpaged();
        List<Product> byKey = productRepository.findByNameContainingIgnoreCaseOrBrantBrandContainingIgnoreCaseOrTypeTypeContainingIgnoreCase(key, key, key, pageable);
        assertEquals(1,byKey.size());
    }
    @Test
    public void testSearchKyeNotCorrectProduct(){
        String key = "not Exist";
        Pageable pageable = Pageable.unpaged();
        List<Product> byKey = productRepository.findByNameContainingIgnoreCaseOrBrantBrandContainingIgnoreCaseOrTypeTypeContainingIgnoreCase(key, key, key, pageable);
        assertEquals(0,byKey.size());
    }

    @Test
    public void testAddEmptyProduct(){
        Product product1 = new Product();
        assertThrows(DataIntegrityViolationException.class, () -> {
            productRepository.save(product1);
        });
    }
}