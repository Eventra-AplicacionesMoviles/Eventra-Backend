package pe.edu.upc.eventra.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.payment_service.model.entities.Refund;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}

