package com.springhealthtrack.api.dtos;

import com.springhealthtrack.api.domains.Appointment;
import com.springhealthtrack.api.enums.SpecialtyEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Long id,
        Long doctorId,

        @NotNull
        Long patientId,

        @NotNull
        @Future
        LocalDateTime appointmentDate,

        SpecialtyEnum specialty){
        public AppointmentDTO(Appointment newAppointment) {
            this(newAppointment.getId(), newAppointment.getDoctor().getId(), newAppointment.getPatient().getId(), newAppointment.getAppointmentDate(), newAppointment.getDoctor().getSpecialty());
        }
}
