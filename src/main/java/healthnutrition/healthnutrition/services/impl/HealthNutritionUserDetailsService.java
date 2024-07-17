package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class HealthNutritionUserDetailsService implements UserDetailsService {

    private final UserRepositories userRepositories;
    public HealthNutritionUserDetailsService(UserRepositories userRepository) {
        this.userRepositories = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepositories
                .findByEmail(email)
                .map(HealthNutritionUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found"));
    }
// map form user to userDetails service
    private static UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(map(user.getRole()))
                .build();
    }
    // autorization from role
    private static GrantedAuthority map(UserRoleEnum userRoleEntity){
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.name());

    }
}
