package pe.edu.upc.eventra.payment_service.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.payment_service.model.dtos.PaymentRequest;
import pe.edu.upc.eventra.payment_service.model.dtos.PaymentResponse;
import pe.edu.upc.eventra.payment_service.model.dtos.ReservationResponse;
import pe.edu.upc.eventra.payment_service.model.dtos.StatusResponse;
import pe.edu.upc.eventra.payment_service.model.entities.Payment;
import pe.edu.upc.eventra.payment_service.model.entities.Status;
import pe.edu.upc.eventra.payment_service.repository.PaymentRepository;
import pe.edu.upc.eventra.payment_service.repository.ReservationClient;
import pe.edu.upc.eventra.payment_service.repository.StatusRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationClient reservationClient;
    private final StatusRepository statusRepository;

    @Transactional
    public PaymentResponse addPayment(PaymentRequest paymentRequest) {
        reservationClient.getReservationById(paymentRequest.getReservationId());
        Status status = statusRepository.findById(paymentRequest.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + paymentRequest.getStatusId()));

        Payment payment = Payment.builder()
                .reservationID(paymentRequest.getReservationId())
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .paymentStatus(status)
                .paymentDate(paymentRequest.getPaymentDate())
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment added: {}", savedPayment);
        return mapToPaymentResponse(savedPayment);
    }

    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    public PaymentResponse getPaymentById(long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return mapToPaymentResponse(payment);
    }

    @Transactional
    public PaymentResponse updatePayment(long id, PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));

        reservationClient.getReservationById(paymentRequest.getReservationId());
        Status status = statusRepository.findById(paymentRequest.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + paymentRequest.getStatusId()));

        payment.setReservationID(paymentRequest.getReservationId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setPaymentStatus(status);
        payment.setPaymentDate(paymentRequest.getPaymentDate());

        Payment updatedPayment = paymentRepository.save(payment);
        log.info("Updated Payment: {}", updatedPayment);
        return mapToPaymentResponse(updatedPayment);
    }

    public void deletePayment(long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
        log.info("Deleted Payment with id: {}", id);
    }

    private PaymentResponse mapToPaymentResponse(Payment payment) {
        ReservationResponse reservationResponse;

        try {
            reservationResponse = reservationClient.getReservationById(payment.getReservationID());
        } catch (FeignException e) {
            log.error("Service is unavailable, unable to fetch details", e);
            reservationResponse = ReservationResponse.builder()
                    .reservationId(null)
                    .user(null)
                    .ticket(null)
                    .quantity(null)
                    .reservationDate(null)
                    .build();
        }

        return PaymentResponse.builder()
                .paymentId(payment.getPaymentID())
                .reservation(reservationResponse)
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(StatusResponse.builder()
                        .statusId(payment.getPaymentStatus().getStatusID())
                        .description(payment.getPaymentStatus().getDescription())
                        .build())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
