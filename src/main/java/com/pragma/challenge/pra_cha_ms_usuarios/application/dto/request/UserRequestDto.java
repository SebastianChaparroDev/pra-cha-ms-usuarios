package com.pragma.challenge.pra_cha_ms_usuarios.application.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private String firstName;

    private String lastName;

    private String documentNumber;

    private String celphone;

    private LocalDate birthDay;

    private String email;

    private String pass;

}
