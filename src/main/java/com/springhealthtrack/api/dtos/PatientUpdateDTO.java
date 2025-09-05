package com.springhealthtrack.api.dtos;

import jakarta.validation.constraints.Email;

public record PatientUpdateDTO(
        Long id,
        String name,
        @Email String email,
        String phone,
        AddressDTO address
) {
}
