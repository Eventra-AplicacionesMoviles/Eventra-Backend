package pe.edu.upc.eventra.payment_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.eventra.payment_service.model.dtos.PaymentResponse;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @GetMapping("/api/payments/{id}")
    PaymentResponse getPaymentById(@PathVariable("id") long id);
}

