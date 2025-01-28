package com.kata.kata.service;

import com.kata.kata.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    User registerUser(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User updateUser(Long userId, User userDetails);

    void deleteUser(Long userId);

    List<User> getAllUsers();

    Optional<User> findUserById(Long userId);
}
