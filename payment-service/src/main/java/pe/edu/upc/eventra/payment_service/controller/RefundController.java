package pe.edu.upc.eventra.payment_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.payment_service.model.dtos.RefundRequest;
import pe.edu.upc.eventra.payment_service.model.dtos.RefundResponse;
import pe.edu.upc.eventra.payment_service.service.RefundService;

import java.util.List;

@RestController
@RequestMapping("/api/refunds")
@RequiredArgsConstructor
@Tag(name = "RefundController", description = "API for refund operations")
public class RefundController {
    private final RefundService refundService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new Refund", description = "Adds a new Refund to the system")
    public RefundResponse addRefund(@RequestBody RefundRequest refundRequest) {
        return refundService.addRefund(refundRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Refunds", description = "Retrieves a list of all Refunds")
    public List<RefundResponse> getAllRefunds() {
        return refundService.getAllRefunds();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Refund by ID", description = "Retrieves a specific Refund by ID")
    public RefundResponse getRefundById(@PathVariable("id") long id) {
        return refundService.getRefundById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a Refund", description = "Updates a specific Refund by ID")
    public RefundResponse updateRefund(@PathVariable("id") long id, @RequestBody RefundRequest refundRequest) {
        return refundService.updateRefund(id, refundRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a Refund", description = "Deletes a specific Refund by ID")
    public void deleteRefund(@PathVariable("id") long id) {
        refundService.deleteRefund(id);
    }
}

