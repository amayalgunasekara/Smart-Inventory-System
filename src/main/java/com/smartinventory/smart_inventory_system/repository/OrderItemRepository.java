package com.smartinventory.smart_inventory_system.repository;

import com.smartinventory.smart_inventory_system.entity.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}