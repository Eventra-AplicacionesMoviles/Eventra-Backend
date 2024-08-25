package pe.edu.upc.eventra.reservations_service.model.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {
    private Long reservationId;
    private UserResponse user;
    private TicketResponse ticket;
    private Integer quantity;
    private LocalDateTime reservationDate;
}

