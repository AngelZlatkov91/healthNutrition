package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.entitys.UserEntity;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

    private static UserDetails map(UserEntity user) {
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(map(user.getRole()))
                .build();
    }
    private static GrantedAuthority map(UserRoleEnum userRoleEntity){
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.name());

    }
}
