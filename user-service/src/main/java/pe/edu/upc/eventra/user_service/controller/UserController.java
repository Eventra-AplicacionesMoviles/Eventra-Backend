package pe.edu.upc.eventra.user_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.user_service.model.dtos.UserRequest;
import pe.edu.upc.eventra.user_service.model.dtos.UserResponse;
import pe.edu.upc.eventra.user_service.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "API for user operations")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new user", description = "Adds a new user to the system")
    @ApiResponse(responseCode = "201", description = "User created")
    public void addUser(@RequestBody UserRequest userRequest) {
        this.userService.addUser(userRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all users", description = "Retrieves a list of all users")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a user", description = "Updates a specific user by their ID")
    @ApiResponse(responseCode = "200", description = "User updated")
    public void updateUser(@PathVariable("id") long id, @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a user", description = "Deletes a specific user by their ID")
    @ApiResponse(responseCode = "204", description = "User deleted")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a user by ID", description = "Retrieves a specific user by their ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of user")
    public UserResponse getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a user by email", description = "Retrieves a specific user by their email")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of user")
    public UserResponse getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }
}

