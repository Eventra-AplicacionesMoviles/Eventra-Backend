package pe.edu.upc.eventra.payment_service.model.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private Long reservationId;
    private Double amount;
    private String paymentMethod;
    private Long statusId;
    private LocalDateTime paymentDate;
}

