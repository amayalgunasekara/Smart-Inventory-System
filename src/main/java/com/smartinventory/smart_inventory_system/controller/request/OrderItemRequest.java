package com.smartinventory.smart_inventory_system.controller.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderItemRequest {
    @NotNull
    private Long productId;

    @NotNull @Min(1)
    private Integer quantity;
}