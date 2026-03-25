package com.smartinventory.smart_inventory_system.entity.model;

import com.smartinventory.smart_inventory_system.entity.enums.NotificationStatus;
import com.smartinventory.smart_inventory_system.entity.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notification")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Column(nullable = false, length = 500)
    private String message;

    private LocalDateTime sentDate = LocalDateTime.now();

    private String recipient;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status = NotificationStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}