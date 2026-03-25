package com.smartinventory.smart_inventory_system.controller.respons;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SupplierResponse {
    private Long supplierId;
    private String name;
    private String email;
    private String phone;
    private String address;
}