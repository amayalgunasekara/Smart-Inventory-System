package com.smartinventory.smart_inventory_system.service.serviceImpl;

import com.smartinventory.smart_inventory_system.controller.request.ProductRequest;
import com.smartinventory.smart_inventory_system.controller.respons.ProductResponse;
import com.smartinventory.smart_inventory_system.entity.model.Product;
import com.smartinventory.smart_inventory_system.entity.model.Supplier;
import com.smartinventory.smart_inventory_system.repository.ProductRepository;
import com.smartinventory.smart_inventory_system.repository.SupplierRepository;
import com.smartinventory.smart_inventory_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public ProductResponse createProduct(ProductRequest dto) {
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + dto.getSupplierId()));

        Product product = new Product();
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setReorderLevel(dto.getReorderLevel());
        product.setSupplier(supplier);

        product = productRepository.save(product);
        return mapToResponse(product);
    }

    @Override
    public ProductResponse updateStock(Long productId, Integer newStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        product.setStockQuantity(newStock);
        product = productRepository.save(product);
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getLowStockProducts() {
        return productRepository.findAll().stream()
                .filter(p -> p.getStockQuantity() <= p.getReorderLevel())
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapToResponse(Product p) {
        return new ProductResponse(
                p.getProductId(),
                p.getName(),
                p.getCategory(),
                p.getPrice(),
                p.getStockQuantity(),
                p.getReorderLevel(),
                p.getSupplier().getSupplierId(),
                p.getSupplier().getName()
        );
    }
}