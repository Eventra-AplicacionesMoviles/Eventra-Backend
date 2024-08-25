package pe.edu.upc.eventra.user_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.eventra.user_service.model.dtos.TypeOfUserRequest;
import pe.edu.upc.eventra.user_service.model.dtos.TypeOfUserResponse;
import pe.edu.upc.eventra.user_service.service.TypeOfUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/typeofuser")
@RequiredArgsConstructor
@Tag(name = "TypeOfUserController", description = "API for user type operations")
public class TypeOfUserController {

    private final TypeOfUserService typeOfUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new type of user", description = "Adds a new type of user to the system")
    @ApiResponse(responseCode = "201", description = "Type of user created")
    public void addTypeOfUser(@RequestBody TypeOfUserRequest typeOfUserRequest) {
        this.typeOfUserService.addTypeOfUser(typeOfUserRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all types of users", description = "Retrieves a list of all types of users")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of user types")
    public List<TypeOfUserResponse> getAllTypeOfUsers() {
        return typeOfUserService.getAllTypeOfUsers();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a type of user", description = "Updates a specific type of user by their ID")
    @ApiResponse(responseCode = "200", description = "Type of user updated")
    public void updateTypeOfUser(@PathVariable("id") long id, @RequestBody TypeOfUserRequest typeOfUserRequest) {
        typeOfUserService.updateTypeOfUser(id, typeOfUserRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@Operation(summary = "Delete a type of user", description = "Deletes a specific type of user by their ID")
    //@ApiResponse(responseCode = "204", description = "Type of user deleted")
    public void deleteTypeOfUser(@PathVariable("id") long id) {
        typeOfUserService.deleteTypeOfUser(id);
    }
}

