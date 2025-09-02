package com.springhealthtrack.api.domain;

import com.springhealthtrack.api.dtos.AddressDTO;
import com.springhealthtrack.api.dtos.DoctorRegistrationDTO;
import com.springhealthtrack.api.dtos.DoctorUpdateDTO;
import com.springhealthtrack.api.enums.SpecialtyEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Doctor")
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    private String email;
    private String phone;
    private String license;

    @Enumerated(EnumType.STRING)
    private SpecialtyEnum specialty;

    @Embedded
    private AddressDTO address;

    private Boolean active;

    public Doctor(DoctorRegistrationDTO doctor) {
        this.active = true;
        this.name = doctor.name();
        this.email = doctor.email();
        this.phone = doctor.phone();
        this.license = doctor.license();
        this.specialty = doctor.specialty();
        this.address = new AddressDTO(doctor.address());
    }

    public void updateInformation(@Valid DoctorUpdateDTO data) {
        if (data.name() != null) this.name = data.name();
        if (data.phone() != null) this.phone = data.phone();
        if (data.address() != null) this.address.updateInformation(data.address());
    }

    public void delete() {
        this.active = false;
    }
}