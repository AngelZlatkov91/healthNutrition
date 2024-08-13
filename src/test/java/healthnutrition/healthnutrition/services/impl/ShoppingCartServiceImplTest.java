package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.dto.cartDTOS.*;
import healthnutrition.healthnutrition.models.dto.productDTOS.ProductCreateDTO;
import healthnutrition.healthnutrition.models.entitys.*;
import healthnutrition.healthnutrition.models.enums.DeliveryAddress;
import healthnutrition.healthnutrition.models.enums.DeliveryFirmEnum;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
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
    @Autowired
    private DeliveryDataRepositories deliveryDataRepositories;



    @BeforeEach
    void setUp(){
        productInCartRepositories.deleteAll();
        shoppingCartRepositories.deleteAll();
        productRepository.deleteAll();
        brandRepository.deleteAll();
        typeRepository.deleteAll();
        userRepositories.deleteAll();
        deliveryDataRepositories.deleteAll();
        saveData();
    }
    @AfterEach
    void cleanUp(){

        shoppingCartRepositories.deleteAll();
        productInCartRepositories.deleteAll();
        productRepository.deleteAll();
        brandRepository.deleteAll();
        typeRepository.deleteAll();
        userRepositories.deleteAll();
        deliveryDataRepositories.deleteAll();
    }

    @Test
    public void testAddProductToCart(){
        shoppingCartService.addProductToShoppingCart(getProductName());

        assertEquals(1,shoppingCartService.productInCart().getProducts().size());

    }
    @Test
    public void testAddSameProductAndIncreaseQuantity(){
        Optional<Product> save = productRepository.findByName(createProduct2().getName());

        shoppingCartService.addProductToShoppingCart(save.get().getName());
        shoppingCartService.addProductToShoppingCart(save.get().getName());
        Map<String, ProductInCartDTO> products = shoppingCartService.productInCart().getProducts();
        ProductInCartDTO protein = products.get("Protein");
        assertEquals(2,protein.getQuantity());

    }
    @Test
    public void testIncreaseQuantity(){
        shoppingCartService.addProductToShoppingCart(getProductName());
        shoppingCartService.increase("fat burner");
        Map<String, ProductInCartDTO> products = shoppingCartService.productInCart().getProducts();
        ProductInCartDTO fatBurner = products.get("fat burner");
        assertEquals(2,fatBurner.getQuantity());
        assertEquals(100.00,shoppingCartService.calculateTotalPrice());
        setUp();
    }
    @Test
    public void testDecreaseQuantity(){

        Optional<Product> byName = productRepository.findByName("Vitamin");
        shoppingCartService.addProductToShoppingCart(byName.get().getName());
        shoppingCartService.increase("Vitamin");
        shoppingCartService.decrease("Vitamin");
        Map<String, ProductInCartDTO> products = shoppingCartService.productInCart().getProducts();
        ProductInCartDTO vitamin = products.get("Vitamin");
        assertEquals(1,vitamin.getQuantity());

    }

    @Test
    public void testRemoveProductFromCart(){
        shoppingCartService.addProductToShoppingCart(getProductName());
        shoppingCartService.remove("fat burner");
        int size = shoppingCartService.productInCart().getProducts().size();
        assertEquals(0,size);

    }




   private void saveData(){
       brandRepository.save(brand());
       typeRepository.save(type());
       productRepository.save(createProduct());
       productRepository.save(createProduct2());
       productRepository.save(createProduct3());

   }


    private String getProductName(){
        Optional<Product> byName = productRepository.findByName("fat burner");
        return byName.get().getName();
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
    private Product createProduct2(){
        Optional<BrandProduct> byBrand = brandRepository.findByBrand("AMIX");
        Optional<TypeProduct> byType = typeRepository.findByType("FAT");
        Product product = new Product();
        product.setName("Protein");
        product.setDescription("Protein");
        product.setPrice(50.00);
        product.setImageUrl("imageURl");
        product.setType(byType.get());
        product.setBrant(byBrand.get());
        product.setUuid(UUID.randomUUID());
        return product;
    }
    private Product createProduct3(){
        Optional<BrandProduct> byBrand = brandRepository.findByBrand("AMIX");
        Optional<TypeProduct> byType = typeRepository.findByType("FAT");
        Product product = new Product();
        product.setName("Vitamin");
        product.setDescription("Vitamin");
        product.setPrice(50.00);
        product.setImageUrl("imageURl");
        product.setType(byType.get());
        product.setBrant(byBrand.get());
        product.setUuid(UUID.randomUUID());
        return product;
    }

    private User user (){
        User user = new User();
        user.setFullName("Angel");
        user.setEmail("ango_z@abv.bg");
        user.setPhone("0893451813");
        user.setRole(UserRoleEnum.USER);
        user.setPassword("123456");

        return user;
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

    private DeliveryDataDTO address(){
        DeliveryDataDTO address = new DeliveryDataDTO();
            address.setCity("Sofia");
            address.setPostCode("1000");
            address.setAddress("str. Prilep 69");
            address.setFirm("EKONT");
            address.setDeliveryAddress("ADDRESS");
            address.add();
        return address;
    }






}