package pe.edu.upc.eventra.reservations_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private UserResponse organizer;
    private CategoryEventResponse categoryEvent;
    private String url; // Nuevo campo agregado
}

