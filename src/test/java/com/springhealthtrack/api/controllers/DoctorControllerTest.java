package com.springhealthtrack.api.controllers;

import com.springhealthtrack.api.dtos.AddressDTO;
import com.springhealthtrack.api.dtos.DoctorRegistrationDTO;
import com.springhealthtrack.api.enums.SpecialtyEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoctorRegistrationDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldValidateCorrectDoctorRegistrationDTO() {
        AddressDTO address = new AddressDTO(
                "Street A",
                "Neighborhood B",
                "12345678",
                "City C",
                "State D",
                "123",
                "Near park"
        );

        DoctorRegistrationDTO dto = new DoctorRegistrationDTO(
                "Dr. John",
                "john@example.com",
                "999999999",
                "12345",
                SpecialtyEnum.CARDIOLOGY,
                address
        );

        Set<ConstraintViolation<DoctorRegistrationDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailWhenLicenseIsInvalid() {
        AddressDTO address = new AddressDTO(
                "Street A",
                "Neighborhood B",
                "12345678",
                "City C",
                "State D",
                "123",
                "Near park"
        );

        DoctorRegistrationDTO dto = new DoctorRegistrationDTO(
                "Dr. John",
                "john@example.com",
                "999999999",
                "12",
                SpecialtyEnum.CARDIOLOGY,
                address
        );

        Set<ConstraintViolation<DoctorRegistrationDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {
        AddressDTO address = new AddressDTO(
                "Street A",
                "Neighborhood B",
                "12345678",
                "City C",
                "State D",
                "123",
                "Near park"
        );

        DoctorRegistrationDTO dto = new DoctorRegistrationDTO(
                "Dr. John",
                "invalid-email",
                "999999999",
                "12345",
                SpecialtyEnum.CARDIOLOGY,
                address
        );

        Set<ConstraintViolation<DoctorRegistrationDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenZipCodeIsInvalid() {
        AddressDTO address = new AddressDTO(
                "Street A",
                "Neighborhood B",
                "1234",
                "City C",
                "State D",
                "123",
                "Near park"
        );

        DoctorRegistrationDTO dto = new DoctorRegistrationDTO(
                "Dr. John",
                "john@example.com",
                "999999999",
                "12345",
                SpecialtyEnum.CARDIOLOGY,
                address
        );

        Set<ConstraintViolation<DoctorRegistrationDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}
