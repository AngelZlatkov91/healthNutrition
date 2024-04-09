package healthnutrition.healthnutrition.config;

import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import healthnutrition.healthnutrition.services.impl.HealthNutritionUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private  final String rememberMeKey;

    public SecurityConfig (@Value("${healthNutrition.remember.me.key}") String rememberMe) {
        this.rememberMeKey =rememberMe;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                // define which urls are visible by which users
                authorizeRequests -> authorizeRequests
                        // all static resources which are situated in js images css are available for anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // allow actuator endpoints

                        .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                        // allow anyone to see the home page the registration and the login form
                        .requestMatchers("/","/users/login","/users/register", "/users/login-error").permitAll()
                        .requestMatchers("/products/all").permitAll()
                        .requestMatchers(HttpMethod.GET,"/offer/**").permitAll()
                        .requestMatchers("error").permitAll()
                        .requestMatchers("/product/add").hasRole(UserRoleEnum.ADMIN.name())
                        .requestMatchers("/brands").hasRole(UserRoleEnum.ADMIN.name())
                        .requestMatchers("/type").hasRole(UserRoleEnum.ADMIN.name())
                        .requestMatchers("/add-article").hasRole(UserRoleEnum.ADMIN.name())
                        // all other requests are authenticated
                        .anyRequest().authenticated()).formLogin(
                formLogin -> {
                    formLogin
                            // redirect here when we access something which is not allowed
                            //also this is the page where we perform login.
                            .loginPage("/users/login")
                            // the names of the input fields (in ou case auth-login.html
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout-> {
                    logout
                            // the url where we should post something in order to perform logout
                            .logoutUrl("/users/logout")
                            // where to go logged out?
                            .logoutSuccessUrl("/")
                            // invalidate the http session
                            .invalidateHttpSession(true);
                }
        ).rememberMe(
                rememberMe -> {
                    rememberMe
                            .key(rememberMeKey)
                            .rememberMeParameter("rememberme")
                            .rememberMeCookieName("rememberme");
                }
        );
        // TODO:
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepositories userRepository) {
        // this service translates between the mobilele users and roles
        // to representation which spring security understands;
        return new HealthNutritionUserDetailsService(userRepository);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
