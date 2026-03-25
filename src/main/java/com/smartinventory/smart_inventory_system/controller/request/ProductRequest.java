package com.smartinventory.smart_inventory_system.controller.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductRequest {
    @NotBlank
    private String name;

    private String category;

    @NotNull @Positive
    private Double price;

    @NotNull @Min(0)
    private Integer stockQuantity;

    @NotNull @Min(0)
    private Integer reorderLevel;

    @NotNull
    private Long supplierId;
}