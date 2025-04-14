package edu.comillas.icai.gitt.pat.spring.p5.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO#7
 * Añade 2 tests unitarios adicionales que validen diferentes casos
 * (no variaciones del mismo caso) de registro con datos inválidos
 */

class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testInvalidEmail() {
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "no-es-un-email",
                Role.USER, "aaaaaaA1");

        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    public void testNullRole() {
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "usuario@email.com",
                null, "aaaaaaA1");

        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("role")));
    }



}