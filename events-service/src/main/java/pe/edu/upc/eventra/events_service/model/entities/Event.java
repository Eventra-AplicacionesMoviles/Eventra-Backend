package pe.edu.upc.eventra.events_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "id_organizer")
    private Long organizerId;

    @Column(name = "url", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String url; // Nuevo campo agregado

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private CategoryEvent categoryEvent;
}

