package com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.input.rest.controller;

import com.pragma.challenge.pra_cha_ms_usuarios.application.handler.IUserHandler;
import com.pragma.challenge.pra_cha_ms_usuarios.application.dto.request.UserRequestDto;
import com.pragma.challenge.pra_cha_ms_usuarios.application.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "Users", description = "Gestiona los usuarios del sistema")
public class UserRestController {

    private final IUserHandler userHandler;

    public UserRestController(IUserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @PostMapping("/owner")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Crear cuenta de propietario",
            description = "Permite al administrador crear una nueva cuenta par aun Propietario de restaurante",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Propietario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "403", description = "No tiene permisos para realizar esta acción"),
            @ApiResponse(responseCode = "409", description = "El correo o documento ya están registrados")
    })
    public ResponseEntity<Void> createOwner(@Valid @RequestBody UserRequestDto request){
        userHandler.createOwner(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Buscar usuario por Id",
            description = "Permite al aadministrador buscar un usuario por id",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ususario encontrado"),
            @ApiResponse(responseCode = "400", description = "Usuario no encontrado"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.findById(id));
    }
}
