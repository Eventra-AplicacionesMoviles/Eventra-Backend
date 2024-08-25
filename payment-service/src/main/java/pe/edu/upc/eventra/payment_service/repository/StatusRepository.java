package pe.edu.upc.eventra.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.payment_service.model.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}

