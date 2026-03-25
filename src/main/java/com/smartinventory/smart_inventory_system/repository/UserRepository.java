package com.smartinventory.smart_inventory_system.repository;

import com.smartinventory.smart_inventory_system.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}