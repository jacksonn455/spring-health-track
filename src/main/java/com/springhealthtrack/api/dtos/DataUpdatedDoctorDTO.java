package com.springhealthtrack.api.dtos;

import com.springhealthtrack.api.domains.Doctor;
import com.springhealthtrack.api.enums.SpecialtyEnum;

public record DataUpdatedDoctorDTO(
        Long id,
        String name,
        String email,
        String phone,
        String license,
        SpecialtyEnum specialty,
        AddressDTO address) {

    public DataUpdatedDoctorDTO(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getLicense(),
                doctor.getSpecialty(),
                doctor.getAddress()
        );
    }
}
