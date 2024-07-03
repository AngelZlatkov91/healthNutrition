package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.cartDTOS.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.cartDTOS.ShoppingCartDTO;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.entitys.BrandProduct;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.TypeProduct;
import healthnutrition.healthnutrition.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartServiceImplTest {
    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;
    @Autowired
    private ShoppingCartRepositories shoppingCartRepositories;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private ProductInCartRepositories productInCartRepositories;

    private ShoppingCartDTO shoppingCartDTO;


    @BeforeEach
    void setUp(){
        shoppingCartDTO = new ShoppingCartDTO();
        productInCartRepositories.deleteAll();
        shoppingCartRepositories.deleteAll();
        productRepository.deleteAll();
        brandRepository.deleteAll();
        typeRepository.deleteAll();
        userRepositories.deleteAll();
    }
    @AfterEach
    void cleanUp(){
        productInCartRepositories.deleteAll();
        shoppingCartRepositories.deleteAll();
        productRepository.deleteAll();
        brandRepository.deleteAll();
        typeRepository.deleteAll();
        userRepositories.deleteAll();
    }

    @Test
    public void testAddProductToCart(){
        shoppingCartService.addProductToShoppingCart(getProductUUID());

        assertEquals(1,shoppingCartService.productInCart().getProducts().size());
    }
    @Test
    public void testAddSameProductAndIncreaseQuantity(){
        Product byUuid = productRepository.findByUuid(getProductUUID());
        shoppingCartService.addProductToShoppingCart(byUuid.getUuid());
        shoppingCartService.addProductToShoppingCart(byUuid.getUuid());
        Map<String, ProductInCartDTO> products = shoppingCartService.productInCart().getProducts();
        ProductInCartDTO fatBurner = products.get("fat burner");
        assertEquals(2,fatBurner.getQuantity());

    }





    private UUID getProductUUID(){
        brandRepository.save(brand());
        typeRepository.save(type());
        productRepository.save(createProduct());
        Optional<Product> byName = productRepository.findByName("fat burner");
        return byName.get().getUuid();
    }
    private Product createProduct(){
        Optional<BrandProduct> byBrand = brandRepository.findByBrand("AMIX");
        Optional<TypeProduct> byType = typeRepository.findByType("FAT");
        Product product = new Product();
                product.setName("fat burner");
                product.setDescription("fatBurning");
                product.setPrice(50.00);
                product.setImageUrl("imageURl");
                product.setType(byType.get());
                product.setBrant(byBrand.get());
                product.setUuid(UUID.randomUUID());
        return product;
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