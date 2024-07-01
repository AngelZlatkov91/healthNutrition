package healthnutrition.healthnutrition.services.impl;

import healthnutrition.healthnutrition.models.entitys.User;
import healthnutrition.healthnutrition.models.enums.UserRoleEnum;
import healthnutrition.healthnutrition.repositories.UserRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HealthNutritionUserDetailsServiceTest {
    private HealthNutritionUserDetailsService serviceTest;
    @Mock
    private UserRepositories mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceTest = new HealthNutritionUserDetailsService(mockUserRepository);

    }

    @Test
    void testMock() {

        Optional<User> user = mockUserRepository.findByEmail("text@adsa.com");

        Assertions.assertFalse(user.isPresent());
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows
                (UsernameNotFoundException.class,
                        () -> serviceTest.loadUserByUsername("pesho@softuni.bg"));

    }

    @Test
    void testUserFoundException() {
        // Arrange
        User testUser = createTestUser();
        when(mockUserRepository.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails userDetails = serviceTest.loadUserByUsername(testUser.getEmail());

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUser.getEmail(),userDetails.getUsername());


    }

    private static User createTestUser() {
        User user = new User();
        user.setFullName("angel");
        user.setPassword("topSicret");
        user.setEmail("angel@zlatkov.com");
        user.setPhone("0893451813");
        user.setRole(UserRoleEnum.ADMIN);
        return user;
    }
}