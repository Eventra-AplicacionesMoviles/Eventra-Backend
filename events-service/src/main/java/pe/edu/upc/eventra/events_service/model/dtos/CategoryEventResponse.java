package pe.edu.upc.eventra.events_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEventResponse {
    private Long id;
    private String name;
}

