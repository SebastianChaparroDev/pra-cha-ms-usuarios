package com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.configuration;

import com.pragma.challenge.pra_cha_ms_plazoleta.domain.api.IUserServicePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.challenge.pra_cha_ms_plazoleta.domain.usecase.UserUseCase;
import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.output.jpa.adapter.UserPersistenceAdapter;
import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.challenge.pra_cha_ms_plazoleta.infraestructure.output.jpa.repository.IUserRepository;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class BeanConfiguration {

    @Bean
    public IUserPersistencePort userPersistencePort(
            IUserRepository userRepository,
            IUserEntityMapper userEntityMapper,
            PasswordEncoder passwordEncoder) {
        return new UserPersistenceAdapter(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort){
        return new UserUseCase(userPersistencePort);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return mapper;
//    }
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.modules(new JavaTimeModule());
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}
