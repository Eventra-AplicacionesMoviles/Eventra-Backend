package pe.edu.upc.eventra.notification_service.model.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
    private Long userId;
    private String message;
    private boolean isRead;
    private LocalDateTime sentDate;
}


