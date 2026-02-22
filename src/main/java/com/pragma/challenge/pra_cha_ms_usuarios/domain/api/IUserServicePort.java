package com.pragma.challenge.pra_cha_ms_usuarios.domain.api;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;

public interface IUserServicePort {
    void createOwner(User owner);
}
