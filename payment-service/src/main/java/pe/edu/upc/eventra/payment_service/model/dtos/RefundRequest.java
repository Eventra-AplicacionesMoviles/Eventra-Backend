package pe.edu.upc.eventra.payment_service.model.dtos;

import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundRequest {
    private Long paymentId;
    private Double refundAmount;
    private Long statusId;
    private LocalDateTime refundDate;
}

