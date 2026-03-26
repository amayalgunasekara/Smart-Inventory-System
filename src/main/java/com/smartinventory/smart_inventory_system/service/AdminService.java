package com.smartinventory.smart_inventory_system.service;

import com.smartinventory.smart_inventory_system.entity.model.Admin;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin createAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Optional<Admin> getAdminById(Long id);
    Optional<Admin> findByUsername(String username);
}