package com.springhealthtrack.api.dtos;

import com.springhealthtrack.api.enums.SpecialtyEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegistrationDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,7}", message = "License must be between 4 and 7 digits")
        String license,

        @NotNull
        SpecialtyEnum specialty,

        @NotNull
        @Valid
        AddressDTO address
) {
}
