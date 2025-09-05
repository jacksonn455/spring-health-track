package com.springhealthtrack.api.validations;

import com.springhealthtrack.api.dtos.AppointmentDTO;

public interface AppointmentValidatorInterface {

    void validate(AppointmentDTO appointmentDTO);
}
