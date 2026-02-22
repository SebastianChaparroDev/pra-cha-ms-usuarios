package com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.mapper;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.Role;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {


    @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    UserEntity toEntity(User user);

    @Mapping(source = "role", target = "role", qualifiedByName = "stringToRole")
    User toUser(UserEntity entity);

    @Named("roleToString")
    default String roleToString(Role role){
        return role != null ? role.name() : null;
    }

    @Named("stringToRole")
    default Role stringToRole(String role){
        return role != null ? Role.valueOf(role) : null;
    }
}
