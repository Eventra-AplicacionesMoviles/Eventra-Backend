package pe.edu.upc.eventra.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.eventra.user_service.model.dtos.auth.AuthResponse;
import pe.edu.upc.eventra.user_service.model.dtos.auth.LoginRequest;
import pe.edu.upc.eventra.user_service.model.dtos.auth.RegisterRequest;
import pe.edu.upc.eventra.user_service.model.entities.TypeOfUser;
import pe.edu.upc.eventra.user_service.model.entities.User;
import pe.edu.upc.eventra.user_service.repository.TypeOfUserRepository;
import pe.edu.upc.eventra.user_service.repository.UserRepository;
import pe.edu.upc.eventra.user_service.shared.Jwt.JwtService;
import pe.edu.upc.eventra.user_service.shared.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TypeOfUserRepository typeOfUserRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {

        TypeOfUser userType = typeOfUserRepository.findById(request.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfUser not found with id: " + request.getTypeId()));
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .typeOfUser(userType)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}