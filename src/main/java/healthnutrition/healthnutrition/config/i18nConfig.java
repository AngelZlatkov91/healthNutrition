package healthnutrition.healthnutrition.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;


@Configuration
public class i18nConfig {


    @Bean
    // CHANGE LOCALE LANG
    public LocaleResolver localeResolver(){
        return new CookieLocaleResolver("lang");
    }

    @Bean
    // he read http request PARAMETARS - LANG
    public LocaleChangeInterceptor localeChangeInterceptor(){
     LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
     localeChangeInterceptor.setParamName("lang");
     return localeChangeInterceptor;
    }

    //message source show the path to the files i18n ,he reeds this keys
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
            messageSource.setBasename("classpath:i18n/messages");
            messageSource.setDefaultEncoding("UTF-8");
            messageSource.setDefaultLocale(Locale.ENGLISH);
            return messageSource;
    }

}
