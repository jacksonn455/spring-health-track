package com.springhealthtrack.api.controllers;

import com.springhealthtrack.api.dtos.AppointmentDTO;
import com.springhealthtrack.api.dtos.GetDetailsAppointmentDTO;
import com.springhealthtrack.api.services.AppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity createAppointment(@RequestBody @Valid AppointmentDTO appointment) {
        var dto = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(dto);
    }
}
