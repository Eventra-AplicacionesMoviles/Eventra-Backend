package pe.edu.upc.eventra.reservations_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private Long ticketID;
    private EventResponse event;
    private Double price;
    private Integer totalAvailable;
    private String category;
    private String description;
}


