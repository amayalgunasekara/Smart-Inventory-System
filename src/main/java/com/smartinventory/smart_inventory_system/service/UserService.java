package com.smartinventory.smart_inventory_system.service;

import com.smartinventory.smart_inventory_system.entity.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> findByEmail(String email);
}