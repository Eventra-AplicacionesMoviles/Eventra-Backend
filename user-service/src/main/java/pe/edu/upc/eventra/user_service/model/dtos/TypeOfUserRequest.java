package pe.edu.upc.eventra.user_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOfUserRequest {
    private String description;
}
