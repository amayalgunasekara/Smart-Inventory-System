package com.smartinventory.smart_inventory_system.controller.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SupplierRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank @Email
    private String email;

    private String phone;
    private String address;
}