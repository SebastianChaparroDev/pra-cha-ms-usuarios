package com.pragma.challenge.pra_cha_ms_usuarios.domain.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String celphone;
    private LocalDate birthDay;
    private String email;
    private String pass;
    private Role role;

    public boolean isOfLegalAge(){
        return birthDay.plusYears(18).isBefore(LocalDate.now()) ||
                birthDay.plusYears(18).isEqual(LocalDate.now());
    }
}
