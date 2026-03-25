package com.smartinventory.smart_inventory_system.controller.respons;

import com.smartinventory.smart_inventory_system.entity.enums.NotificationStatus;
import com.smartinventory.smart_inventory_system.entity.enums.NotificationType;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NotificationResponse {
    private Long notificationId;
    private NotificationType type;
    private String message;
    private LocalDateTime sentDate;
    private String recipient;
    private NotificationStatus status;
}