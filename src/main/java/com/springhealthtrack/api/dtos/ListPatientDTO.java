package com.springhealthtrack.api.dtos;

import com.springhealthtrack.api.domains.Patient;

public record ListPatientDTO(
        Long id,
        String name,
        String email,
        String phone
) {
    public ListPatientDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone());
    }
}
