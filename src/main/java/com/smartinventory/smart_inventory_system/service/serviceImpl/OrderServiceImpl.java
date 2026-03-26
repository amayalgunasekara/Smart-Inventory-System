package com.smartinventory.smart_inventory_system.service.serviceImpl;

import com.smartinventory.smart_inventory_system.controller.request.OrderItemRequest;
import com.smartinventory.smart_inventory_system.controller.request.OrderRequest;
import com.smartinventory.smart_inventory_system.controller.respons.OrderItemResponse;
import com.smartinventory.smart_inventory_system.controller.respons.OrderResponse;
import com.smartinventory.smart_inventory_system.controller.request.NotificationRequest;
import com.smartinventory.smart_inventory_system.entity.model.*;
import com.smartinventory.smart_inventory_system.entity.enums.*;
import com.smartinventory.smart_inventory_system.repository.*;
import com.smartinventory.smart_inventory_system.service.NotificationService;
import com.smartinventory.smart_inventory_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final NotificationService notificationService;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItemRequest itemReq : request.getItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Insufficient stock for " + product.getName());
            }

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(product.getPrice());
            orderItems.add(item);

            totalAmount += product.getPrice() * itemReq.getQuantity();

            // Update stock immediately
            int newStock = product.getStockQuantity() - itemReq.getQuantity();
            product.setStockQuantity(newStock);
            productRepository.save(product);

            // Low stock alert
            if (newStock <= product.getReorderLevel()) {
                NotificationRequest lowReq = new NotificationRequest();
                lowReq.setType(NotificationType.LOW_STOCK);
                lowReq.setMessage("Low stock alert: " + product.getName() + " (Stock: " + newStock + ")");
                lowReq.setRecipient("admin@smartinventory.com");
                notificationService.createNotification(lowReq);
            }
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);
        order = orderRepository.save(order);   // Cascade saves OrderItems

        // Order confirmation notification
        NotificationRequest confReq = new NotificationRequest();
        confReq.setType(NotificationType.ORDER_CONFIRMATION);
        confReq.setMessage("Order #" + order.getOrderId() + " confirmed successfully!");
        confReq.setRecipient(user.getEmail());
        confReq.setOrderId(order.getOrderId());
        notificationService.createNotification(confReq);

        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> items = order.getOrderItems().stream()
                .map(i -> new OrderItemResponse(
                        i.getProduct().getProductId(),
                        i.getProduct().getName(),
                        i.getQuantity(),
                        i.getPrice()))
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getOrderId(),
                order.getUser().getUserId(),
                order.getUser().getName(),
                order.getOrderDate(),
                order.getStatus().name(),
                order.getTotalAmount(),
                items
        );
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToResponse(order);
    }
}