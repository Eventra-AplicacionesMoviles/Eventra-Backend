package pe.edu.upc.eventra.payment_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "refunds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundID;

    @Column(nullable = false)
    private Long paymentID; // ID del pago de la entidad Payment

    @Column(nullable = false)
    private Double refundAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private Status refundStatus;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime refundDate;

    @Override
    public String toString() {
        return "Refund [refundID=" + refundID + ", paymentID=" + paymentID + ", refundAmount=" + refundAmount +
                ", refundStatus=" + refundStatus + ", refundDate=" + refundDate + "]";
    }
}

