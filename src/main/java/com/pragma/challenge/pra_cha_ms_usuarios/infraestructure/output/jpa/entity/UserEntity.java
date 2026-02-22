package com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "document_number", unique = true, nullable = false)
    private String documentNumber;

    @Column(nullable = false, length = 13)
    private String celphone;

    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String pass;

    @Column(nullable = false)
    private String role;
}
