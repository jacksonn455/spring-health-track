package com.springhealthtrack.api.repositories;

import com.springhealthtrack.api.domains.Doctor;
import com.springhealthtrack.api.enums.SpecialtyEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;


public interface DoctorRepository  extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query(value = """
            SELECT d.* FROM doctors d
            WHERE d.active = true
            AND d.specialty = :specialty
            AND d.id NOT IN (
                SELECT a.doctor_id FROM appointments a
                WHERE a.appointment_date = :appointmentDate
            )
            ORDER BY RAND()
            LIMIT 1
            """, nativeQuery = true)
    Doctor chooseRandomDoctorBySpecialty(SpecialtyEnum specialty, LocalDateTime localDateTime);


    @Query("SELECT d.active FROM Doctor d WHERE d.id = :id")
    Boolean findActiveById(@Param("id") Long id);
}
