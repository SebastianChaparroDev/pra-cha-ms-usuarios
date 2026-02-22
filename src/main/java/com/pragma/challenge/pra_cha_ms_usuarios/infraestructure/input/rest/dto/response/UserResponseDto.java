package com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.input.rest.dto.response;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.Role;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String cellphone;
    private LocalDate birthDay;
    private String email;
    private Role role;

}
