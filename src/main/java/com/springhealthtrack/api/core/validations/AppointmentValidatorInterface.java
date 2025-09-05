package com.springhealthtrack.api.core.validations;

import com.springhealthtrack.api.dtos.AppointmentDTO;

public interface AppointmentValidatorInterface {

    void validate(AppointmentDTO appointmentDTO);
}
