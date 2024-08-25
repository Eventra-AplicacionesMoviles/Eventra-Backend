package pe.edu.upc.eventra.events_service.model.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private Long organizerId;
    private Long categoryId;
    private String url;
}
