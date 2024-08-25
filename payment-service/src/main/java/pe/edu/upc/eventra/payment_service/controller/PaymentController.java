package pe.edu.upc.eventra.payment_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.payment_service.model.dtos.PaymentRequest;
import pe.edu.upc.eventra.payment_service.model.dtos.PaymentResponse;
import pe.edu.upc.eventra.payment_service.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "PaymentController", description = "API for payment operations")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new Payment", description = "Adds a new Payment to the system")
    public PaymentResponse addPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.addPayment(paymentRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Payments", description = "Retrieves a list of all Payments")
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Payment by ID", description = "Retrieves a specific Payment by ID")
    public PaymentResponse getPaymentById(@PathVariable("id") long id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a Payment", description = "Updates a specific Payment by ID")
    public PaymentResponse updatePayment(@PathVariable("id") long id, @RequestBody PaymentRequest paymentRequest) {
        return paymentService.updatePayment(id, paymentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a Payment", description = "Deletes a specific Payment by ID")
    public void deletePayment(@PathVariable("id") long id) {
        paymentService.deletePayment(id);
    }
}

