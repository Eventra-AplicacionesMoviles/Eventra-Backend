package pe.edu.upc.eventra.payment_service.model.dtos;

import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundResponse {
    private Long refundId;
    private PaymentResponse payment;
    private Double refundAmount;
    private StatusResponse status;
    private LocalDateTime refundDate;
}
