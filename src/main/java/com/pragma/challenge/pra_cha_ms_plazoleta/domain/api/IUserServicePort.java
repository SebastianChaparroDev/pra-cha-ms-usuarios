package com.pragma.challenge.pra_cha_ms_plazoleta.domain.api;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.model.User;

public interface IUserServicePort {
    void createOwner(User owner);
}
