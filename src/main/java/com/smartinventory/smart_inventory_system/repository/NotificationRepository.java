package com.smartinventory.smart_inventory_system.repository;

import com.smartinventory.smart_inventory_system.entity.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}