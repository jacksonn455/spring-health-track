package com.springhealthtrack.api.services;

import com.springhealthtrack.api.domains.Appointment;
import com.springhealthtrack.api.domains.Doctor;
import com.springhealthtrack.api.domains.Patient;
import com.springhealthtrack.api.dtos.AppointmentDTO;
import com.springhealthtrack.api.repositories.AppointmentRepository;
import com.springhealthtrack.api.repositories.DoctorRepository;
import com.springhealthtrack.api.repositories.PatientRepository;
import com.springhealthtrack.api.core.exceptions.ValidateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentConsumerService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @KafkaListener(
            topics = "appointment-processed",
            groupId = "appointment-consumer-group",
            containerFactory = "appointmentDTOKafkaListenerContainerFactory"
    )
    public void consume(AppointmentDTO dto, Acknowledgment ack) {
        try {
            log.info("Consuming appointment from Kafka: {}", dto);

            Patient patient = patientRepository.findById(dto.patientId())
                    .orElseThrow(() -> new ValidateException("Patient ID not found"));

            Doctor doctor = dto.doctorId() != null
                    ? doctorRepository.getReferenceById(dto.doctorId())
                    : doctorRepository.chooseRandomDoctorBySpecialty(dto.specialty(), dto.appointmentDate());

            Appointment appointment = new Appointment(null, doctor, patient, dto.appointmentDate());
            appointmentRepository.save(appointment);

            log.info("Appointment saved to the database: {}", appointment.getId());

            ack.acknowledge();
        } catch (Exception e) {
            log.error("Error processing appointment: {}", e.getMessage(), e);
        }
    }
}
