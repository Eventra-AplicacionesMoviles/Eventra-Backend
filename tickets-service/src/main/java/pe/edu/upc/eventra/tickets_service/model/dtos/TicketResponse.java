package pe.edu.upc.eventra.tickets_service.model.dtos;

import lombok.*;

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


