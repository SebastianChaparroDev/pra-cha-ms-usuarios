package com.pragma.challenge.pra_cha_ms_usuarios.domain.util;

import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.DuplicateDocumentException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.DuplicateEmailException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.InvalidFieldException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.exception.MinorUserException;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.model.User;
import com.pragma.challenge.pra_cha_ms_usuarios.domain.spi.IUserPersistencePort;


import java.time.LocalDate;

public class UserValidator {

    private UserValidator(){}

    public static void validateRequieredFields(User user, IUserPersistencePort userPersistencePort){
        validateRequiredFields(user);
        validateEmail(user.getEmail());
        validateCelphone(user.getCelphone());
        validateDocumentNumber(user.getDocumentNumber());
        validateLegalAge(user.getBirthDay());
        validateExistsEmail(user, userPersistencePort);
        validateExistsDocument(user, userPersistencePort);
    }

    private static void validateRequiredFields(User user) {
        if (isBlank(user.getFirstName()))
            throw new InvalidFieldException("El nombre es obligatorio");
        if (isBlank(user.getLastName()))
            throw new InvalidFieldException("El apellido es obligatorio");
        if (isBlank(user.getDocumentNumber()))
            throw new InvalidFieldException("El documento es obligatorio");
        if (isBlank(user.getCelphone()))
            throw new InvalidFieldException("El celular es obligatorio");
        if (user.getBirthDay() == null)
            throw new InvalidFieldException("La fecha de nacimiento es obligatoria");
        if (isBlank(user.getEmail()))
            throw new InvalidFieldException("El correo es obligatorio");
        if (isBlank(user.getPass()))
            throw new InvalidFieldException("La clave es obligatoria");
    }

    private static void validateEmail(String email) {
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            throw new InvalidFieldException("El formato del correo no es válido");
    }

    private static void validateCelphone(String celphone) {
        if (!celphone.matches("^\\+?\\d{1,13}$"))
            throw new InvalidFieldException(
                    "El celular debe tener máximo 13 caracteres y puede contener +"
            );
    }

    private static void validateDocumentNumber(String documentNumber) {
        if (!documentNumber.matches("\\d+"))
            throw new InvalidFieldException(
                    "El documento de identidad debe ser únicamente numérico"
            );
    }

    private static void validateLegalAge(LocalDate birthDay) {
        if (!birthDay.plusYears(Constants.YEARS_TO_ADD).isBefore(LocalDate.now().plusDays(1)))
            throw new MinorUserException(
                    "El usuario debe ser mayor de edad"
            );
    }

    private static void validateExistsEmail(User user, IUserPersistencePort userPersistencePort){
        if (userPersistencePort.existsByDocument(user.getDocumentNumber())){
            throw new DuplicateDocumentException("El documento ya se encuentra registrado");
        }
    }

    private static void validateExistsDocument(User user, IUserPersistencePort userPersistencePort) {
        if (userPersistencePort.existsByEmail(user.getEmail())){
            throw new DuplicateEmailException("El correo ya se encuentra resgistrado");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
