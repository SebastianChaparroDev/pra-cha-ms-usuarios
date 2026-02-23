package com.pragma.challenge.pra_cha_ms_usuarios.application.handler;

import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.input.rest.dto.request.UserRequestDto;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.input.rest.dto.response.UserResponseDto;

public interface IUserHandler {
    void createOwner(UserRequestDto requestDto);
    UserResponseDto findById(Long id);
}
