package healthnutrition.healthnutrition.config;
import healthnutrition.healthnutrition.models.entitys.StatisticForSellerProduct;
import healthnutrition.healthnutrition.repositories.StatisticRepositories;
import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MyScheduledTasks {

private final ShoppingCartService shoppingCartService;
private final StatisticRepositories statisticRepositories;

    public MyScheduledTasks(ShoppingCartService shoppingCartService, StatisticRepositories statisticRepositories) {
        this.shoppingCartService = shoppingCartService;
        this.statisticRepositories = statisticRepositories;
    }

    // scheduled task two times per day - 9 - and 21 save static for seller product per day
    @Scheduled(cron = "* 0 9,21 * * *")
    public void QuantitySellerProducts() {
      Integer quantity = Integer.parseInt(this.shoppingCartService.sellerProductQuantity());
        StatisticForSellerProduct statisticForSellerProduct = new StatisticForSellerProduct();
        statisticForSellerProduct.setDate(LocalDate.now());
        statisticForSellerProduct.setQuantity(quantity);
        this.statisticRepositories.save(statisticForSellerProduct);
    }
}
