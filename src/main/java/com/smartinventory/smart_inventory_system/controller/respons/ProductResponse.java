package com.smartinventory.smart_inventory_system.controller.respons;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductResponse {
    private Long productId;
    private String name;
    private String category;
    private Double price;
    private Integer stockQuantity;
    private Integer reorderLevel;
    private Long supplierId;
    private String supplierName;
}