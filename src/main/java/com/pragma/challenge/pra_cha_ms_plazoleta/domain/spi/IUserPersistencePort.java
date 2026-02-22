package com.pragma.challenge.pra_cha_ms_plazoleta.domain.spi;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.model.User;

public interface IUserPersistencePort {
    User saveUser(User user);
    User findById(Long id);
    boolean existsByDocument(String documentNumber);
    boolean existsByEmail(String email);
}
