package com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.input.rest.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "Nombre obligatorio")
    private String firstName;

    @NotBlank(message = "Apellido obligatorio")
    private String lastName;

    @NotBlank(message = "Numero de documento obligatorio")
    @Pattern(regexp = "\\d+", message = "numero de documento debe ser numerico")
    private String documentNumber;

    @NotBlank(message = "Celular obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{1,13}$", message = "Numero de celular invalido, debe ser máximo 13 caracteres incluido el '+'")
    private String celphone;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento no debe ser actual")
    private LocalDate birthDay;

    @NotBlank(message = "Correo obligatorio")
    @Email(message = "Formato de correo inválido")
    private String email;

    @NotBlank(message = "Clave obligatoria")
    @Size(min = 8, message = "La clave debe tener mínimo 8 caracteres")
    private String pass;

}
