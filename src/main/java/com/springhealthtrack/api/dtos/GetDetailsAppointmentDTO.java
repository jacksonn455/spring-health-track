package com.springhealthtrack.api.dtos;

import java.time.LocalDateTime;

public record GetDetailsAppointmentDTO(Long id, Long doctorId, Long patientId, LocalDateTime appointmentDate) {
}
