package com.pragma.challenge.pra_cha_ms_usuarios.domain.spi;

public interface IPassEncoderPort {
    String encode(String rawPassword);
}
