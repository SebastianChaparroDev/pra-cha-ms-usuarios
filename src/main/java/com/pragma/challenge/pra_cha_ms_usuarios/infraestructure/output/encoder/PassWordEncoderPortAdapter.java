package com.pragma.challenge.pra_cha_ms_usuarios.infraestructure.output.encoder;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IPassEncoderPort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassWordEncoderPortAdapter implements IPassEncoderPort {

    private final PasswordEncoder passEncoder;

    public PassWordEncoderPortAdapter(PasswordEncoder passWordEncoder) {
        this.passEncoder = passWordEncoder;
    }

    @Override
    public String encode(String rawPassword) {
        return passEncoder.encode(rawPassword);
    }
}
