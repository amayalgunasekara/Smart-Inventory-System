package com.smartinventory.smart_inventory_system.service.serviceImpl;

import com.smartinventory.smart_inventory_system.entity.enums.Role;
import com.smartinventory.smart_inventory_system.entity.model.Admin;
import com.smartinventory.smart_inventory_system.repository.AdminRepository;
import com.smartinventory.smart_inventory_system.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        admin.setRole(Role.ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}