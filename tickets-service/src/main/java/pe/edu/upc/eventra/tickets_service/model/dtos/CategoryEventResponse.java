package pe.edu.upc.eventra.tickets_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEventResponse {
    private Long id;
    private String name;
}

