package com.alexsys.smartmarket.user.service;

import com.alexsys.smartmarket.user.mapper.UserMapper;
import com.alexsys.smartmarket.user.model.User;
import com.alexsys.smartmarket.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("johndoe");
        testUser.setEmail("johndoe@example.com");
        testUser.setPassword("password123");
    }

    @Test
    void getAllUsers_shouldReturnUsers() {
        // Arrange
        User anotherUser = new User();
        anotherUser.setId(2);
        anotherUser.setUsername("janedoe");
        anotherUser.setEmail("janedoe@example.com");
        anotherUser.setPassword("pass");

        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser, anotherUser));

        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_shouldReturnUser() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));

        // Act
        Optional<User> result = userService.getUserById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("johndoe", result.get().getUsername());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void getUserById_shouldReturnEmptyWhenNotFound() {
        // Arrange
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.getUserById(99);

        // Assert
        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findById(99);
    }

    @Test
    void saveUser_shouldPersistUser() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        User savedUser = userService.saveUser(testUser);

        // Assert
        assertNotNull(savedUser);
        assertEquals("johndoe", savedUser.getUsername());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void updateUser_shouldModifyAndReturnUpdatedUser() {
        // Arrange
        User updatedDetails = new User();
        updatedDetails.setUsername("newname");
        updatedDetails.setEmail("newemail@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // We don’t want real mapping here → mock the mapper
        doAnswer(invocation -> {
            User existing = invocation.getArgument(0);
            User details = invocation.getArgument(1);
            existing.setUsername(details.getUsername());
            existing.setEmail(details.getEmail());
            return null;
        }).when(userMapper).update(any(User.class), any(User.class));

        // Act
        Optional<User> result = userService.updateUser(1, updatedDetails);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("newname", result.get().getUsername());
        assertEquals("newemail@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(testUser);
        verify(userMapper, times(1)).update(testUser, updatedDetails);
    }

    @Test
    void updateUser_shouldReturnEmptyIfNotFound() {
        // Arrange
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.updateUser(99, testUser);

        // Assert
        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findById(99);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUser_shouldCallRepositoryDelete() {
        // Act
        userService.deleteUser(1);

        // Assert
        verify(userRepository, times(1)).deleteById(1);
    }
}
