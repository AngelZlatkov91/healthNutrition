package healthnutrition.healthnutrition.config;

import healthnutrition.healthnutrition.services.ShoppingCartService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduledTasks {

private final ShoppingCartService shoppingCartService;

    public MyScheduledTasks(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Scheduled(cron = "* 0 9,21 * * *")
    public void toUser() {
     this.shoppingCartService.delete();
    }
}
