package pe.edu.upc.eventra.notification_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationID;

    @Column(nullable = false)
    private Long userID; // ID del usuario de la entidad User

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean isRead;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sentDate;

    @Override
    public String toString() {
        return "Notification [notificationID=" + notificationID + ", userID=" + userID + ", message=" + message +
                ", isRead=" + isRead + ", sentDate=" + sentDate + "]";
    }
}
