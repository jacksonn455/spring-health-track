package com.springhealthtrack.api.services;

import com.springhealthtrack.api.domains.Appointment;
import com.springhealthtrack.api.domains.Doctor;
import com.springhealthtrack.api.dtos.AppointmentDTO;
import com.springhealthtrack.api.exceptions.ValidateException;
import com.springhealthtrack.api.repositories.AppointmentRepository;
import com.springhealthtrack.api.repositories.DoctorRepository;
import com.springhealthtrack.api.repositories.PatientRepository;
import com.springhealthtrack.api.validations.AppointmentValidatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired(required = false)
    private List<AppointmentValidatorInterface> validators = new ArrayList<>();

    public AppointmentDTO createAppointment(AppointmentDTO appointment) {
        if(!patientRepository.existsById(appointment.patientId())) {
            throw new ValidateException("Patient ID not found");
        }

        if(appointment.doctorId() != null && !doctorRepository.existsById(appointment.doctorId())) {
            throw new ValidateException("Doctor ID not found");
        }

        if (validators != null) {
            validators.forEach(v -> v.validate(appointment));
        }

        var patient = patientRepository.findById(appointment.patientId())
                .orElseThrow(() -> new ValidateException("Patient ID not found"));

        var doctor = chooseDoctor(appointment);
        Appointment newAppointment = new Appointment(null, doctor, patient, appointment.appointmentDate());
        appointmentRepository.save(newAppointment);

        return new AppointmentDTO(newAppointment);
        }

    private Doctor chooseDoctor(AppointmentDTO appointment) {
        if(appointment.doctorId() != null) {
            return doctorRepository.getReferenceById(appointment.doctorId());
        }

        if (appointment.specialty() == null) {
            throw new ValidateException("Specialty is required when doctor ID is not provided");
        }

        return doctorRepository.chooseRandomDoctorBySpecialty(appointment.specialty(), appointment.appointmentDate());
    };
}