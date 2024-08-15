package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.cartDTOS.*;
import healthnutrition.healthnutrition.models.entitys.*;
import healthnutrition.healthnutrition.repositories.*;

import healthnutrition.healthnutrition.services.RestProductService;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepositories shoppingCartRepositories;
     private final UserRepositories userRepositories;
     private final DeliveryDataRepositories deliveryDataRepositories;
     private final RestProductService restProductService;
     private final ShoppingCartDTO shoppingCartDTO;
     private final ProductInCartRepositories productInCartRepositories;

     private final ModelMapper mapper;





     @Autowired
    public ShoppingCartServiceImpl( ShoppingCartRepositories shoppingCartRepositories, UserRepositories userRepositories, DeliveryDataRepositories deliveryDataRepositories, RestProductService restProductService, ProductInCartRepositories productInCartRepositories, ModelMapper mapper) {
        this.shoppingCartRepositories = shoppingCartRepositories;
        this.userRepositories = userRepositories;
         this.deliveryDataRepositories = deliveryDataRepositories;
         this.restProductService = restProductService;
         this.productInCartRepositories = productInCartRepositories;
         this.mapper = mapper;
         this.shoppingCartDTO = new ShoppingCartDTO();
     }


    @Override
    //add product to cart
    public void addProductToShoppingCart(String name) {
        ProductInCartDTO product = restProductService.findProduct(name);
        if (this.shoppingCartDTO.getProducts().containsKey(product.getName())) {
            this.shoppingCartDTO.getProducts().get(product.getName()).increaseQuantity();
        } else {
            this.shoppingCartDTO.getProducts().put(product.getName(),product);
        }
    }
    @Override
    // remove product from card
    public void remove(String getName) {
        this.shoppingCartDTO.getProducts().remove(getName);
    }

    @Override
    // decrease quantity product
    public void decrease(String getName) {
        if (this.shoppingCartDTO.getProducts().get(getName).getQuantity() > 1) {
            this.shoppingCartDTO.getProducts().get(getName).decreaseQuantity();
        }
    }

    @Override
    // increase quantity product
    public void increase(String getName) {
        this.shoppingCartDTO.getProducts().get(getName).increaseQuantity();
    }

    // select product quantity for scheduling
    @Override
    public String sellerProductQuantity() {
        return this.productInCartRepositories.QuantitySellerProduct();
    }


    @Override
    // calculate the price for all product in cart
    public Double calculateTotalPrice() {
         Double totalPrice = 0.0;
         for (ProductInCartDTO product : this.shoppingCartDTO.getProducts().values()) {
             totalPrice = totalPrice + (product.getQuantity() * product.getPrice());
         }
        return totalPrice;
    }

    @Override
    @Transactional
    public void finalStep(String user,DeliveryDataDTO data) {
        Optional<User> byEmail = this.userRepositories.findByEmail(user);
        ShoppingCart shoppingCart = addProduct(data);
        shoppingCart.setUser(byEmail.get());
        byEmail.get().getShoppingCarts().add(shoppingCart);
        Address address = shoppingCart.getAddress();
        List<ProductInCart> products = shoppingCart.getProducts();
        this.deliveryDataRepositories.save(address);
        this.productInCartRepositories.saveAll(products);
        this.shoppingCartRepositories.save(shoppingCart);

    }

    @Override
    // get al product for current shopping cart
    public ShoppingCartDTO productInCart() {
        return this.shoppingCartDTO;
    }


    protected ShoppingCart addProduct (DeliveryDataDTO data) {
        List<ProductInCart> products = addProductToListForShoppingCart();
        return addDataTpShoppingCart(data, products);
    }

    private ShoppingCart addDataTpShoppingCart(DeliveryDataDTO data,  List<ProductInCart> products) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDeliveryNumber(UUID.randomUUID());
        shoppingCart.setAddress(address(data));
        shoppingCart.setProducts(products);
        shoppingCart.setDate(LocalDate.now());
        shoppingCart.setPrice(calculateTotalPrice());
        this.shoppingCartDTO.empty();
        return shoppingCart;
    }

    private List<ProductInCart> addProductToListForShoppingCart() {
         List<ProductInCart> products = new ArrayList<>();
        for (ProductInCartDTO productFromCart : this.shoppingCartDTO.getProducts().values()) {
           ProductInCart productInCart = this.mapper.map(productFromCart,ProductInCart.class);
           products.add(productInCart);
        }
        return products;
    }

    private Address address(DeliveryDataDTO data) {
         return this.mapper.map(data,Address.class);
    }


}
