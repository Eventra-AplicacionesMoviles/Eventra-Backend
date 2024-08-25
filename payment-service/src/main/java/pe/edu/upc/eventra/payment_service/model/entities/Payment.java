package pe.edu.upc.eventra.payment_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;

    @Column(nullable = false)
    private Long reservationID; // ID de la reserva de la entidad Reservation

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, length = 50)
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private Status paymentStatus;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

    @Override
    public String toString() {
        return "Payment [paymentID=" + paymentID + ", reservationID=" + reservationID + ", amount=" + amount +
                ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate + "]";
    }
}

