package com.pragma.challenge.pra_cha_ms_usuarios.domain.usecase;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.api.IUserServicePort;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.UserNotFoundException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.Role;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IPassEncoderPort;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IUserPersistencePort;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.util.UserValidator;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPassEncoderPort passEncoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPassEncoderPort passEncoderPort){
        this.userPersistencePort = userPersistencePort;
        this.passEncoderPort = passEncoderPort;
    }

    @Override
    public void createOwner(User owner) {
        UserValidator.validateRequieredFields(owner, userPersistencePort);
        owner.setRole(Role.OWNER);
        owner.setPass(passEncoderPort.encode(owner.getPass()));
        userPersistencePort.saveUser(owner);
    }

    @Override
    public User findById(Long id) {
        return userPersistencePort.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No existe usuario con el id" + id));
    }
}
