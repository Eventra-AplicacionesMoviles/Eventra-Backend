package pe.edu.upc.eventra.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.notification_service.model.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

