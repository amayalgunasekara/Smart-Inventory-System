package com.smartinventory.smart_inventory_system.service.serviceImpl;

import com.smartinventory.smart_inventory_system.controller.request.SupplierRequest;
import com.smartinventory.smart_inventory_system.controller.respons.SupplierResponse;
import com.smartinventory.smart_inventory_system.entity.model.Supplier;
import com.smartinventory.smart_inventory_system.repository.SupplierRepository;
import com.smartinventory.smart_inventory_system.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public SupplierResponse createSupplier(SupplierRequest dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());

        supplier = supplierRepository.save(supplier);
        return mapToResponse(supplier);
    }

    @Override
    public SupplierResponse updateSupplier(Long id, SupplierRequest dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));

        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());

        supplier = supplierRepository.save(supplier);
        return mapToResponse(supplier);
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponse getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return mapToResponse(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }

    private SupplierResponse mapToResponse(Supplier s) {
        return new SupplierResponse(s.getSupplierId(), s.getName(), s.getEmail(), s.getPhone(), s.getAddress());
    }
}