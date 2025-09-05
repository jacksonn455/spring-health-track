package com.springhealthtrack.api.validations;

import com.springhealthtrack.api.dtos.AppointmentDTO;
import com.springhealthtrack.api.exceptions.ValidateException;
import com.springhealthtrack.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class DoctorValidator implements AppointmentValidatorInterface{

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(AppointmentDTO appointmentDTO) {
       if(appointmentDTO.doctorId() == null){
              return;
       }

        Boolean activeDoctor = doctorRepository.findActiveById(appointmentDTO.doctorId());
        if(!activeDoctor){
            throw new ValidateException("Doctor is not active");
        }
    }
}
