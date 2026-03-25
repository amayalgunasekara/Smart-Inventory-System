package com.smartinventory.smart_inventory_system.repository;

import com.smartinventory.smart_inventory_system.entity.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}