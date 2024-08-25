package pe.edu.upc.eventra.events_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.events_service.model.entities.CategoryEvent;

public interface CategoryEventRepository extends JpaRepository<CategoryEvent, Long> {
    // Repository methods...
}

