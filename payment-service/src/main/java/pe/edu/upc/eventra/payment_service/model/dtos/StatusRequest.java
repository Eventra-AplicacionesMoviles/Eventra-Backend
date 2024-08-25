package pe.edu.upc.eventra.payment_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusRequest {
    private String description;
}

