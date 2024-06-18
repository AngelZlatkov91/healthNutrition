package healthnutrition.healthnutrition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class HealthnutritionApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthnutritionApplication.class, args);
	}

}
