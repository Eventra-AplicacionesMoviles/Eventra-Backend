package pe.edu.upc.eventra.tickets_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.eventra.tickets_service.model.dtos.EventResponse;

@FeignClient(name = "events-service")
public interface EventClient {
    @GetMapping("/api/events/{id}")
    EventResponse getEventById(@PathVariable("id") long id);
}
