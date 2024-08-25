package pe.edu.upc.eventra.payment_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.payment_service.model.dtos.StatusRequest;
import pe.edu.upc.eventra.payment_service.model.dtos.StatusResponse;
import pe.edu.upc.eventra.payment_service.model.entities.Status;
import pe.edu.upc.eventra.payment_service.repository.StatusRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {
    private final StatusRepository statusRepository;

    @Transactional
    public StatusResponse addStatus(StatusRequest statusRequest) {
        Status status = Status.builder()
                .description(statusRequest.getDescription())
                .build();

        Status savedStatus = statusRepository.save(status);
        log.info("Status added: {}", savedStatus);
        return mapToStatusResponse(savedStatus);
    }

    public List<StatusResponse> getAllStatuses() {
        return statusRepository.findAll().stream()
                .map(this::mapToStatusResponse)
                .collect(Collectors.toList());
    }

    public StatusResponse getStatusById(long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + id));
        return mapToStatusResponse(status);
    }

    @Transactional
    public StatusResponse updateStatus(long id, StatusRequest statusRequest) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + id));

        status.setDescription(statusRequest.getDescription());

        Status updatedStatus = statusRepository.save(status);
        log.info("Updated Status: {}", updatedStatus);
        return mapToStatusResponse(updatedStatus);
    }

    public void deleteStatus(long id) {
        if (!statusRepository.existsById(id)) {
            throw new RuntimeException("Status not found with id: " + id);
        }
        statusRepository.deleteById(id);
        log.info("Deleted Status with id: {}", id);
    }

    private StatusResponse mapToStatusResponse(Status status) {
        return StatusResponse.builder()
                .statusId(status.getStatusID())
                .description(status.getDescription())
                .build();
    }
}

