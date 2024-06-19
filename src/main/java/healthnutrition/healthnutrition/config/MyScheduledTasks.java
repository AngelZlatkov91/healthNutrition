package healthnutrition.healthnutrition.config;

import healthnutrition.healthnutrition.services.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduledTasks {

private final ProductService productService;

    public MyScheduledTasks(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(cron = "* 0 9,21 * * *")
    public String QuantitySellerProducts() {
        return this.productService.sellerProductQuantity();
    }
}
