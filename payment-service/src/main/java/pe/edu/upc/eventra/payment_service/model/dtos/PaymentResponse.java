package pe.edu.upc.eventra.payment_service.model.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long paymentId;
    private ReservationResponse reservation;
    private Double amount;
    private String paymentMethod;
    private StatusResponse status;
    private LocalDateTime paymentDate;
}

