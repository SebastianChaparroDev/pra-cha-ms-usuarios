package com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.output.jpa.adapter;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.model.User;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.output.jpa.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserPersistenceAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    public UserPersistenceAdapter(IUserRepository userRepository, IUserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        return userEntityMapper.toUser(userRepository.save(userEntityMapper.toEntity(user)));
    }

    @Override
    public User findById(Long id) {
        return null;
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
