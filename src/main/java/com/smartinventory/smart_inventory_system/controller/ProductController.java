package com.smartinventory.smart_inventory_system.controller;

import com.smartinventory.smart_inventory_system.controller.request.ProductRequest;
import com.smartinventory.smart_inventory_system.controller.respons.ProductResponse;
import com.smartinventory.smart_inventory_system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponse>> getLowStock() {
        return ResponseEntity.ok(productService.getLowStockProducts());
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductResponse> updateStock(@PathVariable Long id, @RequestParam Integer stockQuantity) {
        return ResponseEntity.ok(productService.updateStock(id, stockQuantity));
    }
}