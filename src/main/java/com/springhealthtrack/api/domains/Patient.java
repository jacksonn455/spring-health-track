package com.springhealthtrack.api.domains;

import com.springhealthtrack.api.dtos.AddressDTO;
import com.springhealthtrack.api.dtos.PatientRegistrationDTO;
import com.springhealthtrack.api.dtos.PatientUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Patient")
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String document;

    @Embedded
    private AddressDTO address;

    private Boolean active;

    public Patient(PatientRegistrationDTO patient) {
        this.active = true;
        this.name = patient.name();
        this.email = patient.email();
        this.phone = patient.phone();
        this.document = patient.document();
        this.address = patient.address() != null ? new AddressDTO(patient.address()) : null;
    }

    public void updateInformation(PatientUpdateDTO data) {
        if (data.name() != null) this.name = data.name();
        if (data.phone() != null) this.phone = data.phone();
        if (data.address() != null) {
            if (this.address == null) {
                this.address = new AddressDTO(data.address());
            } else {
                this.address.updateInformation(data.address());
            }
        }
    }


    public void delete() {
        this.active = false;
    }
}
