package com.smartinventory.smart_inventory_system.controller.respons;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private String userName;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;
    private List<OrderItemResponse> items;
}