package healthnutrition.healthnutrition.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "products.api")
public class ProductAppConfig {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

   public ProductAppConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
   }
}
