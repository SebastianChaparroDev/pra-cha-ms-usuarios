package com.pragma.challenge.pra_cha_ms_usuarios.domain.usecase;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.api.IUserServicePort;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.DuplicateDocumentException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.DuplicateEmailException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.MinorUserException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.Role;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createOwner(User owner) {
        if (!owner.isOfLegalAge()){
            throw new MinorUserException("El Propietario debe ser mayor de edad");
        }
        if (userPersistencePort.existsByDocument(owner.getDocumentNumber())){
            throw new DuplicateDocumentException("El documento ya se encuentra registrado");
        }
        if (userPersistencePort.existsByEmail(owner.getEmail())){
            throw new DuplicateEmailException("El correo ya se encuentra resgistrado");
        }

        owner.setRole(Role.OWNER);
        userPersistencePort.saveUser(owner);
    }
}
