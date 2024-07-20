package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.cartDTOS.*;
import healthnutrition.healthnutrition.models.entitys.*;
import healthnutrition.healthnutrition.repositories.*;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    private final ProductRepository productRepository;
    private final ShoppingCartRepositories shoppingCartRepositories;
     private final UserRepositories userRepositories;
     private final DeliveryDataRepositories deliveryDataRepositories;

     private final ShoppingCartDTO shoppingCartDTO;
     private final ProductInCartRepositories productInCartRepositories;

     private final ModelMapper mapper;





     @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository, ShoppingCartRepositories shoppingCartRepositories, UserRepositories userRepositories, DeliveryDataRepositories deliveryDataRepositories, ProductInCartRepositories productInCartRepositories, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.shoppingCartRepositories = shoppingCartRepositories;
        this.userRepositories = userRepositories;
         this.deliveryDataRepositories = deliveryDataRepositories;
         this.productInCartRepositories = productInCartRepositories;
         this.mapper = mapper;
         this.shoppingCartDTO = new ShoppingCartDTO();
     }


    @Override
    //add product to cart
    public void addProductToShoppingCart(UUID uuid) {
        ProductInCartDTO product = findProduct(uuid);
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



    @Override
    // all shopping cart  from current user
    public ArchiveDTO allShoppingCarts(String user) {
        ArchiveDTO archiveDTO = new ArchiveDTO();
        Optional<User> byEmail = this.userRepositories.findByEmail(user);
        List<ShoppingCart> allByUser = this.shoppingCartRepositories.findAllByUserOrderByDateDesc(byEmail.get());
        List<ArchiveShoppingCartDTO> allCarts = new ArrayList<>();
        for (ShoppingCart shop : allByUser) {
            Double totalPrice = 0.0;
            ArchiveShoppingCartDTO archiveShoppingCartDTO = new ArchiveShoppingCartDTO();
            for (ProductInCart productInCart : shop.getProducts()) {
                ArchiveProductInCartDTO archiveProductInCartDTO = this.mapper.map(productInCart, ArchiveProductInCartDTO.class);
//                archiveProductInCartDTO.setName(productInCart.getName());
//                archiveProductInCartDTO.setQuantity(productInCart.getQuantity());
//                archiveProductInCartDTO.setSinglePrice(productInCart.getSinglePrice());
                totalPrice = totalPrice + (productInCart.getQuantity() * productInCart.getPrice());
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
    // only for admin to see all shopping cart for the day
    public AllOrdersDTO allOrdersToSend() {
         AllOrdersDTO allOrdersDTO = new AllOrdersDTO();
         List<OrderDTO> orderDTOList = new ArrayList<>();
        List<ShoppingCart> allByDate = this.shoppingCartRepositories.findAllByDate(LocalDate.now());
        for (ShoppingCart cart : allByDate) {
            User user = cart.getUser();
            Address userAddress = cart.getAddress();
            String address = String.format("%s %s %s",userAddress.getCity(),userAddress.getPostCode(),userAddress.getAddress());
            String deliver = String.format("%s %s %.2f",userAddress.getFirm(),userAddress.getDeliveryAddress(),userAddress.getPriceForDelivery());
            OrderDTO orderDTO =this.mapper.map(user,OrderDTO.class);
//            orderDTO.setFullName(user.getFullName());
//            orderDTO.setEmail(user.getEmail());
//            orderDTO.setPhone(user.getPhone());
            orderDTO.setAddress(address);
            orderDTO.setDeliver(deliver);
            ArchiveShoppingCartDTO archiveShoppingCartDTO = new ArchiveShoppingCartDTO();
            List<ArchiveProductInCartDTO> archive = new ArrayList<>();
            Double price = 0.0;
            for (ProductInCart pr : cart.getProducts()) {
                ArchiveProductInCartDTO archiveProductInCartDTO = this.mapper.map(pr,ArchiveProductInCartDTO.class);
//                archiveProductInCartDTO.setName(pr.getName());
//                archiveProductInCartDTO.setQuantity(pr.getQuantity());
//                archiveProductInCartDTO.setSinglePrice(pr.getSinglePrice());
                archive.add(archiveProductInCartDTO);
                price = price + (pr.getQuantity() * pr.getPrice());
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
        ProductInCartDTO product = this.mapper.map(byUuid,ProductInCartDTO.class);
//        product.setName(byUuid.getName());
//        product.setPrice(byUuid.getPrice());
        product.increaseQuantity();
        return product;
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
