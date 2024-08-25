package pe.edu.upc.eventra.user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upc.eventra.user_service.model.dtos.TypeOfUserRequest;
import pe.edu.upc.eventra.user_service.model.dtos.TypeOfUserResponse;
import pe.edu.upc.eventra.user_service.model.entities.TypeOfUser;
import pe.edu.upc.eventra.user_service.repository.TypeOfUserRepository;
import pe.edu.upc.eventra.user_service.shared.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypeOfUserService {
    private final TypeOfUserRepository typeOfUserRepository;

    public void addTypeOfUser(TypeOfUserRequest typeOfUserRequest) {
        var typeOfUser = TypeOfUser.builder()
                .description(typeOfUserRequest.getDescription())
                .build();

        typeOfUserRepository.save(typeOfUser);
        log.info("Type of User added: {}", typeOfUser);
    }

    public List<TypeOfUserResponse> getAllTypeOfUsers() {
        var typeOfUsers = typeOfUserRepository.findAll();
        return typeOfUsers.stream().map(this::mapToTypeOfUserResponse).toList();
    }

    public void updateTypeOfUser(long id, TypeOfUserRequest typeOfUserRequest) {
        TypeOfUser existingTypeOfUser = typeOfUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User type not found with id " + id));
        existingTypeOfUser.setDescription(typeOfUserRequest.getDescription());
        typeOfUserRepository.save(existingTypeOfUser);
        log.info("Updated User Type: {}", existingTypeOfUser);
    }

    public void deleteTypeOfUser(long id) {
        typeOfUserRepository.deleteById(id);
        log.info("Deleted User Type with id {}", id);
    }

    private TypeOfUserResponse mapToTypeOfUserResponse(TypeOfUser typeOfUser) {
        return TypeOfUserResponse.builder()
                .typeId(typeOfUser.getTypeId())
                .description(typeOfUser.getDescription())
                .build();
    }
}

