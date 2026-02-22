package com.pragma.challenge.pra_cha_ms_plazoleta.application.handler;

import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.input.rest.dto.request.UserRequestDto;

public interface IUserHandler {
    void createOwner(UserRequestDto requestDto);
}
