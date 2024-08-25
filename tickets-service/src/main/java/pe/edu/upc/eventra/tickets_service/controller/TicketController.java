package pe.edu.upc.eventra.tickets_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.tickets_service.model.dtos.TicketRequest;
import pe.edu.upc.eventra.tickets_service.model.dtos.TicketResponse;
import pe.edu.upc.eventra.tickets_service.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Tag(name = "TicketController", description = "API for ticket operations")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new ticket", description = "Adds a new ticket to the system")
    public TicketResponse addTicket(@RequestBody TicketRequest ticketRequest) {
        return ticketService.addTicket(ticketRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all tickets", description = "Retrieves a list of all tickets")
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get ticket by ID", description = "Retrieves a specific ticket by ID")
    public TicketResponse getTicketById(@PathVariable("id") long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/event/{eventID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get tickets by event ID", description = "Retrieves a list of tickets by event ID")
    public List<TicketResponse> getTicketsByEventID(@PathVariable("eventID") Long eventID) {
        return ticketService.getTicketsByEventID(eventID);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a ticket", description = "Updates a specific ticket by ID")
    public TicketResponse updateTicket(@PathVariable("id") long id, @RequestBody TicketRequest ticketRequest) {
        return ticketService.updateTicket(id, ticketRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a ticket", description = "Deletes a specific ticket by ID")
    public void deleteTicket(@PathVariable("id") long id) {
        ticketService.deleteTicket(id);
    }
}

