package com.smartinventory.smart_inventory_system.service.serviceImpl;

import com.smartinventory.smart_inventory_system.controller.request.NotificationRequest;
import com.smartinventory.smart_inventory_system.controller.respons.NotificationResponse;
import com.smartinventory.smart_inventory_system.entity.model.Notification;
import com.smartinventory.smart_inventory_system.entity.model.Order;
import com.smartinventory.smart_inventory_system.entity.enums.NotificationStatus;
import com.smartinventory.smart_inventory_system.repository.NotificationRepository;
import com.smartinventory.smart_inventory_system.repository.OrderRepository;
import com.smartinventory.smart_inventory_system.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final OrderRepository orderRepository;

    @Override
    public NotificationResponse createNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setType(request.getType());
        notification.setMessage(request.getMessage());
        notification.setRecipient(request.getRecipient());
        notification.setStatus(NotificationStatus.PENDING);

        if (request.getOrderId() != null) {
            orderRepository.findById(request.getOrderId()).ifPresent(notification::setOrder);
        }

        notification = notificationRepository.save(notification);

        // Simulate sending (in real app → email/SMS via RabbitMQ later)
        notification.setStatus(NotificationStatus.SENT);
        notification.setSentDate(LocalDateTime.now());
        notification = notificationRepository.save(notification);

        return mapToResponse(notification);
    }

    private NotificationResponse mapToResponse(Notification n) {
        return new NotificationResponse(
                n.getNotificationId(),
                n.getType(),
                n.getMessage(),
                n.getSentDate(),
                n.getRecipient(),
                n.getStatus()
        );
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}