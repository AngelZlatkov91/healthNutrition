package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.ProductInCartDTO;
import healthnutrition.healthnutrition.models.dto.ShoppingCartDTO;
import healthnutrition.healthnutrition.models.entitys.Product;
import healthnutrition.healthnutrition.models.entitys.ShoppingCart;
import healthnutrition.healthnutrition.models.entitys.UserEntity;
import healthnutrition.healthnutrition.repositories.ProductRepository;
import healthnutrition.healthnutrition.repositories.ShoppingCartRepositories;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    private final ProductRepository productRepository;
    private final ShoppingCartRepositories shoppingCartRepositories;
     private final UserRepositories userRepositories;

     private final ShoppingCartDTO shoppingCartDTO;





     @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository, ShoppingCartRepositories shoppingCartRepositories, UserRepositories userRepositories) {
        this.productRepository = productRepository;
        this.shoppingCartRepositories = shoppingCartRepositories;
        this.userRepositories = userRepositories;
         this.shoppingCartDTO = new ShoppingCartDTO();
     }


    @Override
    public void addProductToShoppingCart(UUID uuid) {
        ProductInCartDTO product = findProduct(uuid);
        if (this.shoppingCartDTO.getProducts().containsKey(product.getName())) {
            this.shoppingCartDTO.getProducts().get(product.getName()).increaseQuantity();
        } else {
            this.shoppingCartDTO.getProducts().put(product.getName(),product);
        }
    }


    @Override
    public double calculateTotalPrice() {
         double totalPrice = 0.0;
         for (ProductInCartDTO product : this.shoppingCartDTO.getProducts().values()) {
             totalPrice = totalPrice + (product.getQuantity() * product.getPrice());
         }
        return totalPrice;
    }

    @Override
    public UUID finalStep(String user) {
        ShoppingCart shoppingCart = addProduct(user);
        return shoppingCart.getDeliveryNumber();
    }

    @Override
    public ShoppingCartDTO productInCart() {
        return this.shoppingCartDTO;
    }

    @Override
    public void remove(String getName) {
        this.shoppingCartDTO.getProducts().remove(getName);
    }

    @Override
    public void decrease(String getName) {
        this.shoppingCartDTO.getProducts().get(getName).decreaseQuantity();
    }

    @Override
    public void increase(String getName) {
        this.shoppingCartDTO.getProducts().get(getName).increaseQuantity();
    }

    @Override
    public List<ShoppingCartDTO> allShoppingCarts(String user) {
        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(user);
        List<ShoppingCart> allByUser = this.shoppingCartRepositories.findAllByUser(byEmail.get());
        List<ShoppingCartDTO> allCarts = new ArrayList<>();
        for (ShoppingCart shop : allByUser) {
            ShoppingCartDTO shoppingCartDTO1 = new ShoppingCartDTO();

        }

        return null;
    }


    private ProductInCartDTO findProduct(UUID uuid) {
        Product byUuid = this.productRepository.findByUuid(uuid);
        ProductInCartDTO product = new ProductInCartDTO();
        product.setName(byUuid.getName());
        product.setPrice(byUuid.getPrice());
        product.increaseQuantity();
        return product;
    }

    private ShoppingCart addProduct (String user) {
        Map<Integer,Product> products = new HashMap<>();
        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(user);


        for (ProductInCartDTO productFromCart : this.shoppingCartDTO.getProducts().values()) {
            Optional<Product> byName = this.productRepository.findByName(productFromCart.getName());
           products.put(productFromCart.getQuantity(),byName.get());
        }
        this.shoppingCartDTO.empty();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDeliveryNumber(UUID.randomUUID());
        shoppingCart.setUser(byEmail.get());
        shoppingCart.setProducts(products);
        shoppingCart.setDate(LocalDate.now());
        shoppingCart.setPrice(calculateTotalPrice());
        byEmail.get().getShoppingCarts().add(shoppingCart);
        this.shoppingCartRepositories.save(shoppingCart);
        return shoppingCart;
    }


}
