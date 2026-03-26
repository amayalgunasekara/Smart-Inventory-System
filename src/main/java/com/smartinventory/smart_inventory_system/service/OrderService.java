package com.smartinventory.smart_inventory_system.service;

import com.smartinventory.smart_inventory_system.controller.request.OrderRequest;
import com.smartinventory.smart_inventory_system.controller.respons.OrderResponse;
import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
}