package com.pragma.challenge.pra_cha_ms_usuarios.domain.usecase;




import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.DuplicateDocumentException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.DuplicateEmailException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.MinorUserException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.Role;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User validUser;

    @BeforeEach
    void setUp() {
        validUser = new User();
        validUser.setFirstName("Juan");
        validUser.setLastName("Pérez");
        validUser.setDocumentNumber("123456789");
        validUser.setCelphone("+573005698325");
        validUser.setBirthDay(LocalDate.of(1990, 5, 15));
        validUser.setEmail("juan@correo.com");
        validUser.setPass("clave1234");
    }

    @Test
    @DisplayName("Debe crear propietario exitosamente con datos válidos")
    void createUserValidData() {
        when(userPersistencePort.existsByEmail(any())).thenReturn(false);
        when(userPersistencePort.existsByDocument(any())).thenReturn(false);
        when(userPersistencePort.saveUser(any())).thenReturn(validUser);

        assertDoesNotThrow(() -> userUseCase.createOwner(validUser));

        assertEquals(Role.OWNER, validUser.getRole());
        verify(userPersistencePort, times(1)).saveUser(validUser);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el usuario es menor de edad (17 años)")
    void createMinorOwner() {
        validUser.setBirthDay(LocalDate.now().minusYears(17));

        assertThrows(MinorUserException.class,
                () -> userUseCase.createOwner(validUser));

        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el usuario tiene exactamente 17 años y 364 días")
    void createMinorUserOneDay() {
        validUser.setBirthDay(LocalDate.now().minusYears(18).plusDays(1));

        assertThrows(MinorUserException.class,
                () -> userUseCase.createOwner(validUser));
    }

    @Test
    @DisplayName("Debe permitir crear propietario con exactamente 18 años")
    void createLegalOfAgeOwner() {
        validUser.setBirthDay(LocalDate.now().minusYears(18));

        when(userPersistencePort.existsByEmail(any())).thenReturn(false);
        when(userPersistencePort.existsByDocument(any())).thenReturn(false);
        when(userPersistencePort.saveUser(any())).thenReturn(validUser);

        assertDoesNotThrow(() -> userUseCase.createOwner(validUser));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el correo ya está registrado")
    void createOwnerDuplicateEmail() {
        when(userPersistencePort.existsByEmail("juan@correo.com")).thenReturn(true);

        assertThrows(DuplicateEmailException.class,
                () -> userUseCase.createOwner(validUser));

        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el documento ya está registrado")
    void createOwnerDuplicateDocument() {
        lenient().when(userPersistencePort.existsByEmail(any())).thenReturn(false);
        when(userPersistencePort.existsByDocument("123456789")).thenReturn(true);

        assertThrows(DuplicateDocumentException.class,
                () -> userUseCase.createOwner(validUser));

        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    @DisplayName("El rol debe ser PROPIETARIO siempre, sin importar lo que venga en la solicitud")
    void createOwnerIgnoreRol() {
        validUser.setRole(Role.ADMIN); // intenta asignar otro rol

        when(userPersistencePort.existsByEmail(any())).thenReturn(false);
        when(userPersistencePort.existsByDocument(any())).thenReturn(false);
        when(userPersistencePort.saveUser(any())).thenReturn(validUser);

        userUseCase.createOwner(validUser);

        assertEquals(Role.OWNER, validUser.getRole());
    }

    @Test
    @DisplayName("La validación de mayor de edad tiene precedencia sobre las demás validaciones")
    void createMinorOwnerFirtsValidation() {
        validUser.setBirthDay(LocalDate.now().minusYears(16));
        assertThrows(MinorUserException.class,
                () -> userUseCase.createOwner(validUser));

        verify(userPersistencePort, never()).existsByEmail(any());
    }
}