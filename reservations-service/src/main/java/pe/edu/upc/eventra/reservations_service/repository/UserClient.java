package pe.edu.upc.eventra.reservations_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.eventra.reservations_service.model.dtos.UserResponse;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("api/users/{id}")
    UserResponse getUserById(@PathVariable("id") long id);
}

