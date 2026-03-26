package com.smartinventory.smart_inventory_system.controller;

import com.smartinventory.smart_inventory_system.entity.model.Admin;
import com.smartinventory.smart_inventory_system.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> create(@Valid @RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAll() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getById(@PathVariable Long id) {
        return adminService.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}