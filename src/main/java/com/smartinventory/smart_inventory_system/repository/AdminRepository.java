package com.smartinventory.smart_inventory_system.repository;

import com.smartinventory.smart_inventory_system.entity.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByUsername(String username);
    Optional<Admin> findByUsername(String username);
}