package pe.edu.upc.eventra.user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upc.eventra.user_service.model.dtos.UserRequest;
import pe.edu.upc.eventra.user_service.model.dtos.UserResponse;
import pe.edu.upc.eventra.user_service.model.entities.TypeOfUser;
import pe.edu.upc.eventra.user_service.model.entities.User;
import pe.edu.upc.eventra.user_service.repository.TypeOfUserRepository;
import pe.edu.upc.eventra.user_service.repository.UserRepository;
import pe.edu.upc.eventra.user_service.shared.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final TypeOfUserRepository typeOfUserRepository;

    public void addUser(UserRequest request) {
        TypeOfUser userType = typeOfUserRepository.findById(request.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfUser not found with id: " + request.getTypeId()));
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .typeOfUser(userType)
                .url(request.getUrl()) // Nuevo campo agregado
                .build();
        userRepository.save(user);
        log.info("User added: {}", user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    public void updateUser(long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        TypeOfUser userType = typeOfUserRepository.findById(userRequest.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfUser not found with id: " + userRequest.getTypeId()));

        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setTypeOfUser(userType);
        existingUser.setUrl(userRequest.getUrl()); // Nuevo campo agregado

        userRepository.save(existingUser);
        log.info("Updated User: {}", existingUser);
    }

    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        log.info("Deleted User with id {}", id);
    }

    public UserResponse getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToUserResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse.SimpleTypeOfUserResponse typeResponse = UserResponse.SimpleTypeOfUserResponse.builder()
                .typeId(user.getTypeOfUser().getTypeId())
                .description(user.getTypeOfUser().getDescription())
                .build();
        return UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .typeOfUser(typeResponse)
                .url(user.getUrl()) // Nuevo campo agregado
                .build();
    }
}