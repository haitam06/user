package com.alexsys.smartmarket.user.repository;

import com.alexsys.smartmarket.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        // Create a test user
        testUser = new User();
        testUser.setUsername("johndoe");
        testUser.setEmail("johndoe@example.com");
        testUser.setPassword("password123");
    }

    @Test
    void save_shouldPersistUser() {
        // Act
        User savedUser = userRepository.save(testUser);

        // Assert
        assertNotNull(savedUser.getId());
        assertEquals("johndoe", savedUser.getUsername());
        assertEquals("johndoe@example.com", savedUser.getEmail());
    }

    @Test
    void findById_shouldReturnUser() {
        // Arrange
        User savedUser = userRepository.save(testUser);

        // Act
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(savedUser.getId(), foundUser.get().getId());
        assertEquals("johndoe", foundUser.get().getUsername());
    }

    @Test
    void findAll_shouldReturnAllUsers() {
        // Arrange
        userRepository.save(testUser);

        User anotherUser = new User();
        anotherUser.setUsername("janedoe");
        anotherUser.setEmail("janedoe@example.com");
        anotherUser.setPassword("securePass");
        userRepository.save(anotherUser);

        // Act
        List<User> users = userRepository.findAll();

        // Assert
        assertEquals(2, users.size());
    }

    @Test
    void delete_shouldRemoveUser() {
        // Arrange
        User savedUser = userRepository.save(testUser);

        // Act
        userRepository.deleteById(savedUser.getId());
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());

        // Assert
        assertFalse(deletedUser.isPresent());
    }

    @Test
    void update_shouldModifyUser() {
        // Arrange
        User savedUser = userRepository.save(testUser);

        // Act - Modify
        savedUser.setEmail("newemail@example.com");
        savedUser.setPassword("newPass123");
        User updatedUser = userRepository.save(savedUser);

        // Assert
        assertEquals("newemail@example.com", updatedUser.getEmail());
        assertEquals("newPass123", updatedUser.getPassword());
        assertEquals(savedUser.getId(), updatedUser.getId());
    }

    @Test
    void findByUsername_shouldReturnCorrectUser() {
        // Arrange
        userRepository.save(testUser);

        // Act
        Optional<User> foundUser = userRepository.findByUsername("johndoe");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("johndoe@example.com", foundUser.get().getEmail());
    }

    @Test
    void findByEmail_shouldReturnCorrectUser() {
        // Arrange
        userRepository.save(testUser);

        // Act
        Optional<User> foundUser = userRepository.findByEmail("johndoe@example.com");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("johndoe", foundUser.get().getUsername());
    }

    @Test
    void save_shouldFailWhenEmailIsNull() {
        testUser.setEmail(null);
    
        assertThrows(Exception.class, () -> {
            userRepository.saveAndFlush(testUser);
        });
    }
    
    }

