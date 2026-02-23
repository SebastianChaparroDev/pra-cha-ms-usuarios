package com.pragma.challenge.pra_cha_ms_usuarios.application.handler.impl;

import com.pragma.challenge.pra_cha_ms_usuarios.application.handler.IUserHandler;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.api.IUserServicePort;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.input.rest.dto.request.UserRequestDto;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.input.rest.dto.response.UserResponseDto;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.input.rest.mapper.IUserDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserDtoMapper userDtoMapper;

    public UserHandler(IUserServicePort userServicePort, IUserDtoMapper userDtoMapper) {
        this.userServicePort = userServicePort;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public void createOwner(UserRequestDto requestDto) {
        User user = userDtoMapper.toUser(requestDto);
        userServicePort.createOwner(user);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return userDtoMapper.toResponseDto(userServicePort.findById(id));
    }
}
