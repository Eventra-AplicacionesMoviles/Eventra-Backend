package pe.edu.upc.eventra.payment_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

