package healthnutrition.healthnutrition.config;

import healthnutrition.healthnutrition.models.entitys.StatisticForSellerProduct;
import healthnutrition.healthnutrition.repositories.StatisticRepositories;
import healthnutrition.healthnutrition.services.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MyScheduledTasks {

private final ProductService productService;
private final StatisticRepositories statisticRepositories;

    public MyScheduledTasks(ProductService productService, StatisticRepositories statisticRepositories) {
        this.productService = productService;
        this.statisticRepositories = statisticRepositories;
    }

    @Scheduled(cron = "* 0 9,21 * * *")
    public void QuantitySellerProducts() {
      Integer quantity = Integer.parseInt(this.productService.sellerProductQuantity());
        StatisticForSellerProduct statisticForSellerProduct = new StatisticForSellerProduct();
        statisticForSellerProduct.setDate(LocalDate.now());
        statisticForSellerProduct.setQuantity(quantity);
        this.statisticRepositories.save(statisticForSellerProduct);
    }
}
