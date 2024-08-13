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
class ProductServiceImplTest {
       @Autowired
       private ProductServiceImpl productService;
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
           productService.addProduct(createProduct());
           assertEquals(1,productRepository.count());
       }
       @Test
       public void testAddSameProduct(){
           brandRepository.save(brand());
           typeRepository.save(type());
           productService.addProduct(createProduct());
           assertThrows(DataIntegrityViolationException.class, () -> {
               productService.addProduct(createProduct());
           });
       }
       @Test
       public void testAddEmptyProduct(){
           ProductCreateDTO productCreateDTO = new ProductCreateDTO();
           assertThrows(NoSuchElementException.class, () -> {
               productService.addProduct(productCreateDTO);
           });
       }
       @Test
       public void testGetProductDetails(){
           brandRepository.save(brand());
           typeRepository.save(type());
           productService.addProduct(createProduct());
           Optional<Product> byName =
                   productRepository.findByName("fat burner");
           ProductDetailsDTO productDetails = productService.getProductDetails(byName.get().getUuid());
           assertEquals(productDetails.getName(),createProduct().getName());
           assertEquals(byName.get().getName(),productDetails.getName());
       }
       @Test
       public void testDeleteExistingProductByUUID(){
           brandRepository.save(brand());
           typeRepository.save(type());
           productService.addProduct(createProduct());
           Optional<Product> byName =
                   productRepository.findByName("fat burner");
           productService.deleteProduct(byName.get().getName());
           assertEquals(0,productRepository.count());
       }
       @Test
       public void testDeleteNotExistingProduct(){
           brandRepository.save(brand());
           typeRepository.save(type());
           productService.addProduct(createProduct());

              productService.deleteProduct("test");
           assertEquals(1,productRepository.count());

       }
       @Test
       public void testGetAllProductWithSearchKeyEmpty(){
           brandRepository.save(brand());
           typeRepository.save(type());
           productService.addProduct(createProduct());
           List<ProductDetailsDTO> allProducts = productService.getAllProducts("");
           assertEquals(allProducts.size(),productRepository.count());
       }
    @Test
    public void testGetAllProductWithSearchKeyCorrect(){
        brandRepository.save(brand());
        typeRepository.save(type());
        productService.addProduct(createProduct());
        List<ProductDetailsDTO> allProducts = productService.getAllProducts("amix");
        ProductDetailsDTO productDetailsDTO = allProducts.get(0);
        assertEquals(productDetailsDTO.getName(),createProduct().getName());
    }
    @Test
    public void testGetAllProductWithSearchKeyNotExist(){
        brandRepository.save(brand());
        typeRepository.save(type());
        productService.addProduct(createProduct());
        List<ProductDetailsDTO> allProducts = productService.getAllProducts("not");

        assertEquals(0,allProducts.size());
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