package pe.edu.upc.eventra.reservations_service.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.reservations_service.model.dtos.ReservationRequest;
import pe.edu.upc.eventra.reservations_service.model.dtos.ReservationResponse;
import pe.edu.upc.eventra.reservations_service.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Tag(name = "ReservationController", description = "API for reservation operations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new Reservation", description = "Adds a new Reservation to the system")
    public ReservationResponse addReservation(@RequestBody ReservationRequest ticketRequest) {
        return reservationService.addReservation(ticketRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Reservations", description = "Retrieves a list of all Reservations")
    public List<ReservationResponse> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Reservation by ID", description = "Retrieves a specific Reservation by ID")
    public ReservationResponse getReservationById(@PathVariable("id") long id) {
        return reservationService.getReservationById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a ticket", description = "Updates a specific ticket by ID")
    public ReservationResponse updateReservation(@PathVariable("id") long id, @RequestBody ReservationRequest ticketRequest) {
        return reservationService.updateReservation(id, ticketRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a ticket", description = "Deletes a specific ticket by ID")
    public void deleteReservation(@PathVariable("id") long id) {
        reservationService.deleteReservation(id);
    }
}
