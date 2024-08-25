package pe.edu.upc.eventra.payment_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusID;

    @Column(nullable = false, unique = true, length = 100)
    private String description;

    @Override
    public String toString() {
        return "Status [statusID=" + statusID + ", description=" + description + "]";
    }
}

