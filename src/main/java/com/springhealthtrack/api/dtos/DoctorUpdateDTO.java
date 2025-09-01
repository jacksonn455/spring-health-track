package com.springhealthtrack.api.dtos;

import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {

}
