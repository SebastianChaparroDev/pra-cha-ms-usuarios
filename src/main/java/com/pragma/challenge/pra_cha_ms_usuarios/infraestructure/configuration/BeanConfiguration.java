package com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.configuration;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.api.IUserServicePort;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IPassEncoderPort;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IUserPersistencePort;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.usecase.UserUseCase;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.encoder.PassWordEncoderPortAdapter;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.adapter.UserPersistenceAdapter;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.jpa.repository.IUserRepository;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class BeanConfiguration {

    @Bean
    public IUserPersistencePort userPersistencePort(
            IUserRepository userRepository,
            IUserEntityMapper userEntityMapper) {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort, IPassEncoderPort passEncoderPort){
        return new UserUseCase(userPersistencePort, passEncoderPort);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.modules(new JavaTimeModule());
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }

    @Bean
    public IPassEncoderPort passEncoderPort(PasswordEncoder passwordEncoder) {
        return new PassWordEncoderPortAdapter(passwordEncoder);
    }
}
