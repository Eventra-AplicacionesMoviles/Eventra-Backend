package pe.edu.upc.eventra.payment_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusResponse {
    private Long statusId;
    private String description;
}
