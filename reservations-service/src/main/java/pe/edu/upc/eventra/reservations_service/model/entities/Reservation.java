package pe.edu.upc.eventra.reservations_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationID;

    @Column(nullable = false)
    private Long userID; // User ID from another microservice

    @Column(nullable = false)
    private Long ticketID; // Ticket ID from another microservice

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime reservationDate;
}

