package pe.edu.upc.eventra.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.user_service.model.dtos.auth.AuthResponse;
import pe.edu.upc.eventra.user_service.model.dtos.auth.LoginRequest;
import pe.edu.upc.eventra.user_service.model.dtos.auth.RegisterRequest;
import pe.edu.upc.eventra.user_service.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}