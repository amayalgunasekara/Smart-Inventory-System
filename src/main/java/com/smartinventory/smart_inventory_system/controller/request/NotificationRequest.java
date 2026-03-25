package com.smartinventory.smart_inventory_system.controller.request;

import com.smartinventory.smart_inventory_system.entity.enums.NotificationType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NotificationRequest {
    @NotNull
    private NotificationType type;

    @NotBlank
    private String message;

    private String recipient;

    private Long orderId;
}