package com.smartinventory.smart_inventory_system.controller;

import com.smartinventory.smart_inventory_system.controller.request.NotificationRequest;
import com.smartinventory.smart_inventory_system.controller.respons.NotificationResponse;
import com.smartinventory.smart_inventory_system.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> create(@Valid @RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.createNotification(request));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAll() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
}