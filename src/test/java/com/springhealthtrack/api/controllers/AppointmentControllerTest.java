package com.springhealthtrack.api.controllers;

import com.springhealthtrack.api.dtos.AppointmentDTO;
import com.springhealthtrack.api.enums.SpecialtyEnum;
import com.springhealthtrack.api.services.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAppointment_ShouldReturnOkResponse() {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);

        AppointmentDTO request = new AppointmentDTO(
                null,
                1L,
                2L,
                futureDate,
                SpecialtyEnum.CARDIOLOGY
        );

        AppointmentDTO responseMock = new AppointmentDTO(
                10L,
                1L,
                2L,
                futureDate,
                SpecialtyEnum.CARDIOLOGY
        );

        ResponseEntity<?> response = appointmentController.createAppointment(request);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(responseMock);

        verify(appointmentService, times(1)).createAppointment(request);
    }
}