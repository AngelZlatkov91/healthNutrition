package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.*;
import healthnutrition.healthnutrition.models.entitys.*;
import healthnutrition.healthnutrition.repositories.ProductInCartRepositories;
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
     private final ProductInCartRepositories productInCartRepositories;





     @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository, ShoppingCartRepositories shoppingCartRepositories, UserRepositories userRepositories, ProductInCartRepositories productInCartRepositories) {
        this.productRepository = productRepository;
        this.shoppingCartRepositories = shoppingCartRepositories;
        this.userRepositories = userRepositories;
         this.productInCartRepositories = productInCartRepositories;
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
    public ArchiveDTO allShoppingCarts(String user) {
        ArchiveDTO archiveDTO = new ArchiveDTO();
        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(user);
        List<ShoppingCart> allByUser = this.shoppingCartRepositories.findAllByUserOrderByDateDesc(byEmail.get());
        List<ArchiveShoppingCartDTO> allCarts = new ArrayList<>();
        for (ShoppingCart shop : allByUser) {
            Double totalPrice = 0.0;
            ArchiveShoppingCartDTO archiveShoppingCartDTO = new ArchiveShoppingCartDTO();
            for (ProductInCart productInCart : shop.getProducts()) {
                ArchiveProductInCartDTO archiveProductInCartDTO = new ArchiveProductInCartDTO();
                archiveProductInCartDTO.setName(productInCart.getName());
                archiveProductInCartDTO.setQuantity(productInCart.getQuantity());
                archiveProductInCartDTO.setSinglePrice(productInCart.getSinglePrice());
                totalPrice = totalPrice + (productInCart.getQuantity() * productInCart.getSinglePrice());
                archiveShoppingCartDTO.getArchive().add(archiveProductInCartDTO);
            }
            archiveShoppingCartDTO.setDate(shop.getDate());
            archiveShoppingCartDTO.setTotalPrice(totalPrice);
            allCarts.add(archiveShoppingCartDTO);
        }
          archiveDTO.setArchiveShoppingCartDTOS(allCarts);
        return archiveDTO;
    }

    @Override
    public AllOrdersDTO allOrdersToSend() {
         AllOrdersDTO allOrdersDTO = new AllOrdersDTO();
         List<OrderDTO> orderDTOList = new ArrayList<>();
        List<ShoppingCart> allByDate = this.shoppingCartRepositories.findAllByDate(LocalDate.now());
        for (ShoppingCart cart : allByDate) {
            UserEntity user = cart.getUser();
            Address address = cart.getUser().getAddress();
            String addressFormat = String.format("%s %s %s",address.getCity(),address.getPostCode(),address.getAddress());
            String deliverFormat = String.format("%s %s %.2f",address.getFirm(),address.getDeliveryAddress(),address.getPriceForDelivery());
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setFullName(user.getFullName());
            orderDTO.setEmail(user.getEmail());
            orderDTO.setPhone(user.getPhone());
            orderDTO.setAddress(addressFormat);
            orderDTO.setDeliver(deliverFormat);
            ArchiveShoppingCartDTO archiveShoppingCartDTO = new ArchiveShoppingCartDTO();
            List<ArchiveProductInCartDTO> archive = new ArrayList<>();
            Double price = 0.0;
            for (ProductInCart pr : cart.getProducts()) {
                ArchiveProductInCartDTO archiveProductInCartDTO = new ArchiveProductInCartDTO();
                archiveProductInCartDTO.setName(pr.getName());
                archiveProductInCartDTO.setQuantity(pr.getQuantity());
                archiveProductInCartDTO.setSinglePrice(pr.getSinglePrice());
                archive.add(archiveProductInCartDTO);
                price = price + (pr.getQuantity() * pr.getSinglePrice());
            }
            archiveShoppingCartDTO.setTotalPrice(price);
            archiveShoppingCartDTO.setDate(cart.getDate());
            archiveShoppingCartDTO.setArchive(archive);
            orderDTO.setArchiveDTO(archiveShoppingCartDTO);
           orderDTOList.add(orderDTO);
        }
        allOrdersDTO.setOrders(orderDTOList);

        return allOrdersDTO;
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
         List<ProductInCart> products = new ArrayList<>();
        Optional<UserEntity> byEmail = this.userRepositories.findByEmail(user);


        for (ProductInCartDTO productFromCart : this.shoppingCartDTO.getProducts().values()) {
            Optional<Product> byName = this.productRepository.findByName(productFromCart.getName());
           ProductInCart productInCart = new ProductInCart();
           productInCart.setName(byName.get().getName());
           productInCart.setQuantity(productFromCart.getQuantity());
           productInCart.setSinglePrice(byName.get().getPrice());
           products.add(productInCart);
           this.productInCartRepositories.save(productInCart);
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
