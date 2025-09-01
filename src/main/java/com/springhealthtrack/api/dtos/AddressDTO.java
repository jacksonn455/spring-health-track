package com.springhealthtrack.api.dtos;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    @NotBlank
    private String street;

    @NotBlank
    private String neighborhood;

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "Zip code must be exactly 8 digits")
    private String zipCode;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    private String number;

    private String additionalInfo;

    public AddressDTO(AddressDTO address) {
        this.street = address.street;
        this.neighborhood = address.neighborhood;
        this.zipCode = address.zipCode;
        this.city = address.city;
        this.state = address.state;
        this.number = address.number;
        this.additionalInfo = address.additionalInfo;
    }

    public void updateInformation(AddressDTO address) {
        this.street = address.street != null ? address.street : this.street;
        this.neighborhood = address.neighborhood != null ? address.neighborhood : this.neighborhood;
        this.zipCode = address.zipCode != null ? address.zipCode : this.zipCode;
        this.city = address.city != null ? address.city : this.city;
        this.state = address.state != null ? address.state : this.state;
        this.number = address.number != null ? address.number : this.number;
        this.additionalInfo = address.additionalInfo != null ? address.additionalInfo : this.additionalInfo;
    }
}
