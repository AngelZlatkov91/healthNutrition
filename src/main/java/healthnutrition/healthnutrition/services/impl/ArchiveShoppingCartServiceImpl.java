package healthnutrition.healthnutrition.services.impl;
import healthnutrition.healthnutrition.models.dto.cartDTOS.*;
import healthnutrition.healthnutrition.models.entitys.*;
import healthnutrition.healthnutrition.repositories.ShoppingCartRepositories;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.ArchiveShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArchiveShoppingCartServiceImpl implements ArchiveShoppingCartService {
    private final ShoppingCartRepositories shoppingCartRepositories;
    private final UserRepositories userRepositories;
    private final ModelMapper mapper;

    public ArchiveShoppingCartServiceImpl(ShoppingCartRepositories shoppingCartRepositories, UserRepositories userRepositories, ModelMapper mapper) {
        this.shoppingCartRepositories = shoppingCartRepositories;
        this.userRepositories = userRepositories;
        this.mapper = mapper;
    }

    @Override
    // all shopping cart  from current user
    public ArchiveDTO allShoppingCarts(String user) {
        ArchiveDTO archiveDTO = new ArchiveDTO();
        Optional<User> byEmail = this.userRepositories.findByEmail(user);
        List<ShoppingCart> allByUser = this.shoppingCartRepositories.findAllByUserOrderByDateDesc(byEmail.get());
        List<ArchiveShoppingCartDTO> allCarts = getArchiveShoppingCartDTOS(allByUser);
        archiveDTO.setArchiveShoppingCartDTOS(allCarts);
        return archiveDTO;
    }

    private List<ArchiveShoppingCartDTO> getArchiveShoppingCartDTOS(List<ShoppingCart> allByUser) {
        List<ArchiveShoppingCartDTO> allCarts = new ArrayList<>();
        for (ShoppingCart shop : allByUser) {
            Double totalPrice = 0.0;
            ArchiveShoppingCartDTO archiveShoppingCartDTO = new ArchiveShoppingCartDTO();
            for (ProductInCart productInCart : shop.getProducts()) {
                ArchiveProductInCartDTO archiveProductInCartDTO = this.mapper.map(productInCart, ArchiveProductInCartDTO.class);
                totalPrice = totalPrice + (productInCart.getQuantity() * productInCart.getPrice());
                archiveShoppingCartDTO.getArchive().add(archiveProductInCartDTO);
            }
            archiveShoppingCartDTO.setDate(shop.getDate());
            archiveShoppingCartDTO.setTotalPrice(totalPrice);
            allCarts.add(archiveShoppingCartDTO);
        }
        return allCarts;
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
            orderDTO.setAddress(address);
            orderDTO.setDeliver(deliver);
            ArchiveShoppingCartDTO archiveShoppingCartDTO = new ArchiveShoppingCartDTO();
            List<ArchiveProductInCartDTO> archive = new ArrayList<>();
            Double price = 0.0;
            for (ProductInCart pr : cart.getProducts()) {
                ArchiveProductInCartDTO archiveProductInCartDTO = this.mapper.map(pr,ArchiveProductInCartDTO.class);
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

}
