package com.smartinventory.smart_inventory_system.service;

import com.smartinventory.smart_inventory_system.controller.request.SupplierRequest;
import com.smartinventory.smart_inventory_system.controller.respons.SupplierResponse;

import java.util.List;

public interface SupplierService {
    SupplierResponse createSupplier(SupplierRequest dto);
    SupplierResponse updateSupplier(Long id, SupplierRequest dto);
    List<SupplierResponse> getAllSuppliers();
    SupplierResponse getSupplierById(Long id);
    void deleteSupplier(Long id);
}