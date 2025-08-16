package com.alexsys.smartmarket.service;

import com.alexsys.smartmarket.mapper.UserMapper;
import com.alexsys.smartmarket.model.User;
import com.alexsys.smartmarket.repository.UserRepository;

import org.mapstruct.control.MappingControl.Use;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }
    public Optional<User> getUserById(Integer id) { return userRepository.findById(id); }
    public User saveUser(User user) { return userRepository.save(user); }
    public Optional<User> updateUser(Integer id, User userDetails) {
        var existingUserOptional = getUserById(id);
        if (existingUserOptional.isEmpty()) {
            return Optional.empty();
        }
        var existingUser = existingUserOptional.get();
        userMapper.update(existingUser, userDetails);
        return Optional.ofNullable(userRepository.save(existingUser));
    }
    public void deleteUser(Integer id) { userRepository.deleteById(id); }
}
