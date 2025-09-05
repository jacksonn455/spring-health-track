package com.springhealthtrack.api.core.validations;

import com.springhealthtrack.api.dtos.AppointmentDTO;
import com.springhealthtrack.api.core.exceptions.ValidateException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public abstract class AppointmentHoursValidator implements AppointmentValidatorInterface {

    public void validate(AppointmentDTO appointment) {
        var dateAppointment = appointment.appointmentDate();
        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeHours = dateAppointment.getHour() < 7;
        var afterHours = dateAppointment.getHour() > 18;

        if(sunday || beforeHours || afterHours) {
            throw new ValidateException("Appointments can only be scheduled from Monday to Saturday, between 7 AM and 6 PM");
        }
    }
}
