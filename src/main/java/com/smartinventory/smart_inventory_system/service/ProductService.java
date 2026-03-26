package com.smartinventory.smart_inventory_system.service;

import com.smartinventory.smart_inventory_system.controller.request.ProductRequest;
import com.smartinventory.smart_inventory_system.controller.respons.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest dto);
    ProductResponse updateStock(Long productId, Integer newStock);
    List<ProductResponse> getLowStockProducts();
    List<ProductResponse> getAllProducts();
}