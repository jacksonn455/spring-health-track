package com.springhealthtrack.api.dtos;

import com.springhealthtrack.api.domains.Doctor;
import com.springhealthtrack.api.enums.SpecialtyEnum;

public record ListDoctorDTO(
        Long id,
        String name,
        String email,
        String license,
        SpecialtyEnum specialty) {

    public ListDoctorDTO(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getLicense(),
                doctor.getSpecialty()
        );
    }
}