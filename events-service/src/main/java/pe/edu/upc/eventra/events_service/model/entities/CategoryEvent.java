package pe.edu.upc.eventra.events_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_category", nullable = false)
    private String name;
}

