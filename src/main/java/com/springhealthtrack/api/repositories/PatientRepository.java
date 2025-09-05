package com.springhealthtrack.api.repositories;

import com.springhealthtrack.api.domains.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
