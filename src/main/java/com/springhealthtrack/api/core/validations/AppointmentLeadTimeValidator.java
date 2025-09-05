package com.springhealthtrack.api.core.validations;

import com.springhealthtrack.api.dtos.AppointmentDTO;
import com.springhealthtrack.api.core.exceptions.ValidateException;
import org.springframework.stereotype.Component;

@Component
public abstract class AppointmentLeadTimeValidator implements AppointmentValidatorInterface{

    public void validate(AppointmentDTO appointment) {
        var dateAppointment = appointment.appointmentDate();
        var now = java.time.LocalDateTime.now();
        var differenceInMinutes = java.time.Duration.between(now, dateAppointment).toMinutes();

        if(differenceInMinutes < 30) {
            throw new ValidateException("Appointments must be scheduled at least 30 minutes in advance");
        }
    }
}
