package com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.input.rest.mapper;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.model.User;
import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.input.rest.dto.request.UserRequestDto;
import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.input.rest.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserDtoMapper {
    User toUser(UserRequestDto userDto);
    UserResponseDto toResponseDto(User user);
}
