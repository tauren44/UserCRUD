package com.mateacademy.dao;

import com.mateacademy.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    Optional<User> findUserById(Long id);

    List<User> getAllUsers();
}
