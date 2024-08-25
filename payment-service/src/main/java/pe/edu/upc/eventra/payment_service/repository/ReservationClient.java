package pe.edu.upc.eventra.payment_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.eventra.payment_service.model.dtos.ReservationResponse;

@FeignClient(name = "reservations-service")
public interface ReservationClient {
    @GetMapping("/api/reservations/{id}")
    ReservationResponse getReservationById(@PathVariable("id") long id);
}

