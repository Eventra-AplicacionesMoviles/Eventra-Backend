package pe.edu.upc.eventra.events_service.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.eventra.events_service.model.entities.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Find events by the organizer's user ID
    List<Event> findByOrganizerId(Long organizerId);

    // Find events by title containing a specific string
    List<Event> findByTitleContainingIgnoreCase(String title);

    // Find events happening within a specific date range
    List<Event> findByStartDateBetween(LocalDateTime start, LocalDateTime end);

    // Custom JPQL query to find events with a certain category
    @Query("SELECT e FROM Event e WHERE e.categoryEvent.id = :categoryId")
    List<Event> findByCategoryEventId(@Param("categoryId") Long categoryId);
}


