package com.springhealthtrack.api.repositories;

import com.springhealthtrack.api.domains.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
