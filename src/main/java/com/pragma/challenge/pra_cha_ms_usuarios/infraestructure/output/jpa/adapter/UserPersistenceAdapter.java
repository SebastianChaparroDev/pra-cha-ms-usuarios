package com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.adapter;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IUserPersistencePort;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.repository.IUserRepository;

import java.util.Optional;

public class UserPersistenceAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    public UserPersistenceAdapter(IUserRepository userRepository, IUserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User saveUser(User user) {
        return userEntityMapper.toUser(userRepository.save(userEntityMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Long id) {
       return userRepository.findById(id).map(userEntityMapper::toUser);
    }

    @Override
    public boolean existsByDocument(String documentNumber) {
        return userRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
