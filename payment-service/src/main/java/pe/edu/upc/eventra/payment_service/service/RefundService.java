package pe.edu.upc.eventra.payment_service.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.payment_service.model.dtos.PaymentResponse;
import pe.edu.upc.eventra.payment_service.model.dtos.RefundRequest;
import pe.edu.upc.eventra.payment_service.model.dtos.RefundResponse;
import pe.edu.upc.eventra.payment_service.model.dtos.StatusResponse;
import pe.edu.upc.eventra.payment_service.model.entities.Refund;
import pe.edu.upc.eventra.payment_service.model.entities.Status;
import pe.edu.upc.eventra.payment_service.repository.PaymentClient;
import pe.edu.upc.eventra.payment_service.repository.RefundRepository;
import pe.edu.upc.eventra.payment_service.repository.StatusRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class RefundService {
    private final RefundRepository refundRepository;
    private final PaymentClient paymentClient;
    private final StatusRepository statusRepository;

    @Transactional
    public RefundResponse addRefund(RefundRequest refundRequest) {
        paymentClient.getPaymentById(refundRequest.getPaymentId());
        Status status = statusRepository.findById(refundRequest.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + refundRequest.getStatusId()));

        Refund refund = Refund.builder()
                .paymentID(refundRequest.getPaymentId())
                .refundAmount(refundRequest.getRefundAmount())
                .refundStatus(status)
                .refundDate(refundRequest.getRefundDate())
                .build();

        Refund savedRefund = refundRepository.save(refund);
        log.info("Refund added: {}", savedRefund);
        return mapToRefundResponse(savedRefund);
    }

    public List<RefundResponse> getAllRefunds() {
        return refundRepository.findAll().stream()
                .map(this::mapToRefundResponse)
                .collect(Collectors.toList());
    }

    public RefundResponse getRefundById(long id) {
        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found with id: " + id));
        return mapToRefundResponse(refund);
    }

    @Transactional
    public RefundResponse updateRefund(long id, RefundRequest refundRequest) {
        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found with id: " + id));

        paymentClient.getPaymentById(refundRequest.getPaymentId());
        Status status = statusRepository.findById(refundRequest.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + refundRequest.getStatusId()));

        refund.setPaymentID(refundRequest.getPaymentId());
        refund.setRefundAmount(refundRequest.getRefundAmount());
        refund.setRefundStatus(status);
        refund.setRefundDate(refundRequest.getRefundDate());

        Refund updatedRefund = refundRepository.save(refund);
        log.info("Updated Refund: {}", updatedRefund);
        return mapToRefundResponse(updatedRefund);
    }

    public void deleteRefund(long id) {
        if (!refundRepository.existsById(id)) {
            throw new RuntimeException("Refund not found with id: " + id);
        }
        refundRepository.deleteById(id);
        log.info("Deleted Refund with id: {}", id);
    }

    private RefundResponse mapToRefundResponse(Refund refund) {
        PaymentResponse paymentResponse;

        try {
            paymentResponse = paymentClient.getPaymentById(refund.getPaymentID());
        } catch (FeignException e) {
            log.error("Service is unavailable, unable to fetch details", e);
            paymentResponse = PaymentResponse.builder()
                    .paymentId(null)
                    .reservation(null)
                    .amount(null)
                    .paymentMethod(null)
                    .status(null)
                    .paymentDate(null)
                    .build();
        }

        return RefundResponse.builder()
                .refundId(refund.getRefundID())
                .payment(paymentResponse)
                .refundAmount(refund.getRefundAmount())
                .status(StatusResponse.builder()
                        .statusId(refund.getRefundStatus().getStatusID())
                        .description(refund.getRefundStatus().getDescription())
                        .build())
                .refundDate(refund.getRefundDate())
                .build();
    }
}
