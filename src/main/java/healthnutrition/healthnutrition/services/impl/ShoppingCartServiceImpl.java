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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;
    private final ShoppingCartRepositories shoppingCartRepositories;
     private final UserRepositories userRepositories;

    public ShoppingCartServiceImpl(ProductRepository productRepository, ShoppingCartRepositories shoppingCartRepositories, UserRepositories userRepositories) {
        this.productRepository = productRepository;
        this.shoppingCartRepositories = shoppingCartRepositories;
        this.userRepositories = userRepositories;

    }


    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO, UserDetails user) {
            Optional<UserEntity> byEmail = this.userRepositories.findByEmail(user.getUsername());

            List<ProductInCartDTO> products = shoppingCartDTO.getProducts();

            ShoppingCart shoppingCart = new ShoppingCart();

            shoppingCart.setUser(byEmail.get());

            List<Product> products1 = new ArrayList<>();

            for (ProductInCartDTO product : products){
                Optional<Product> currentProduct = this.productRepository.findByName(product.getName());
                products1.add(currentProduct.get());
            }
            shoppingCart.setProducts(products1);
            shoppingCart.setDate(LocalDate.now());
            shoppingCart.setDeliveryNumber(UUID.randomUUID());
            shoppingCart.setPrice(shoppingCartDTO.calculateSumOfAllProducts());
            byEmail.get().setShoppingCart(shoppingCart.getUser().getShoppingCart());
            this.shoppingCartRepositories.save(shoppingCart);
    }
}
