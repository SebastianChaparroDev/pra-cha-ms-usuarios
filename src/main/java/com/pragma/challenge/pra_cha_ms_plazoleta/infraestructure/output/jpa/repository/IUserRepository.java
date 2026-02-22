package com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.output.jpa.repository;

import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
    Optional<UserEntity> findByEmail(String email);

}
