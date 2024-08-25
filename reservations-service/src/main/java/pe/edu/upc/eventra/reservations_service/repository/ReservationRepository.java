package pe.edu.upc.eventra.reservations_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.reservations_service.model.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
