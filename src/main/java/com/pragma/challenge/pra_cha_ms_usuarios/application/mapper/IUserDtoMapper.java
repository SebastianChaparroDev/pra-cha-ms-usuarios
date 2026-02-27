package com.pragma.challenge.pra_cha_ms_usuarios.application.mapper;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;
import com.pragma.challenge.pra_cha_ms_usuarios.application.dto.request.UserRequestDto;
import com.pragma.challenge.pra_cha_ms_usuarios.application.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserDtoMapper {
    User toUser(UserRequestDto userDto);
    UserResponseDto toResponseDto(User user);
}
