package com.smartinventory.smart_inventory_system.controller.respons;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderItemResponse {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}