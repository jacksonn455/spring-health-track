package com.springhealthtrack.api.dtos;

import com.springhealthtrack.api.domains.Patient;

public record DataUpdatedPatientDTO(
        Long id,
        String name,
        String email,
        String phone,
        String document,
        AddressDTO address,
        Boolean active
) {
    public DataUpdatedPatientDTO(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDocument(),
                patient.getAddress(),
                patient.getActive()
        );
    }
}
