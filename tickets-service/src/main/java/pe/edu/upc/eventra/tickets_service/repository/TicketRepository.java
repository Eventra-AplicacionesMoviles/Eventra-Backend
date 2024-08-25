package pe.edu.upc.eventra.tickets_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.tickets_service.model.entities.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventID(Long eventID);
}

