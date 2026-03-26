package com.smartinventory.smart_inventory_system.service;

import com.smartinventory.smart_inventory_system.controller.request.NotificationRequest;
import com.smartinventory.smart_inventory_system.controller.respons.NotificationResponse;
import java.util.List;

public interface NotificationService {
    NotificationResponse createNotification(NotificationRequest request);
    List<NotificationResponse> getAllNotifications();
}