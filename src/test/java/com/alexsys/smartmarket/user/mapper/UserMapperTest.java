package com.alexsys.smartmarket.user.mapper;

import com.alexsys.smartmarket.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    void update_shouldCopyNonNullProperties() {
        // Given
        User target = new User();
        target.setId(1); // Integer
        target.setUsername("old_username");
        target.setEmail("old@email.com");
        target.setPassword("old_password");

        User source = new User();
        source.setUsername("new_username");
        source.setEmail("new@email.com");
        source.setPassword(null); // should not overwrite existing

        // When
        userMapper.update(target, source);

        // Then
        assertEquals(Integer.valueOf(1), target.getId()); // unchanged
        assertEquals("new_username", target.getUsername()); // updated
        assertEquals("new@email.com", target.getEmail());   // updated
        assertEquals("old_password", target.getPassword()); // unchanged (null ignored)
    }

    @Test
    void update_shouldIgnoreAllNulls() {
        // Given
        User target = new User();
        target.setId(2); // Integer
        target.setUsername("username");
        target.setEmail("email@email.com");

        User source = new User(); // all nulls

        // When
        userMapper.update(target, source);

        // Then (nothing should change)
        assertEquals(Integer.valueOf(2), target.getId());
        assertEquals("username", target.getUsername());
        assertEquals("email@email.com", target.getEmail());
    }
}
