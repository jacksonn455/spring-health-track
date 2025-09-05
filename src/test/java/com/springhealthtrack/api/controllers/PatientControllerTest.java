package com.springhealthtrack.api.controllers;

import com.springhealthtrack.api.domains.Patient;
import com.springhealthtrack.api.dtos.*;
import com.springhealthtrack.api.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_ShouldReturnCreatedPatient() {
        AddressDTO address = new AddressDTO(
                "Street A",
                "Neighborhood B",
                "12345678",
                "City C",
                "State D",
                "123",
                "Near park"
        );
        PatientRegistrationDTO registrationDTO = new PatientRegistrationDTO(
                "John Doe",
                "john@example.com",
                "999999999",
                "12345678901",
                address
        );

        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> {
            Patient p = invocation.getArgument(0);
            return new Patient(
                    1L,
                    p.getName(),
                    p.getEmail(),
                    p.getPhone(),
                    p.getDocument(),
                    p.getAddress(),
                    true
            );
        });

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");

        ResponseEntity<?> response = patientController.register(registrationDTO, uriBuilder);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getHeaders().getLocation()).isEqualTo(URI.create("http://localhost:8080/patients/1"));
        assertThat(response.getBody()).isInstanceOf(DataUpdatedPatientDTO.class);

        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void getPatients_ShouldReturnPageOfPatients() {
        Patient patient = mock(Patient.class);
        when(patient.getId()).thenReturn(1L);
        when(patient.getName()).thenReturn("Alice");
        when(patient.getEmail()).thenReturn("alice@example.com");
        when(patient.getPhone()).thenReturn("999999999");
        Page<Patient> page = new PageImpl<>(List.of(patient));

        when(patientRepository.findAll(any(PageRequest.class))).thenReturn(page);

        ResponseEntity<Page<ListPatientDTO>> response = patientController.getPatients(PageRequest.of(0, 10));

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void getPatientById_ShouldReturnPatient() {
        Patient patient = mock(Patient.class);
        when(patientRepository.getReferenceById(1L)).thenReturn(patient);

        ResponseEntity<?> response = patientController.getPatientById(1L);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isInstanceOf(DataUpdatedPatientDTO.class);

        verify(patientRepository, times(1)).getReferenceById(1L);
    }

    @Test
    void updatePatient_ShouldReturnUpdatedPatient() {
        PatientUpdateDTO updateDTO = mock(PatientUpdateDTO.class);
        when(updateDTO.id()).thenReturn(2L);
        Patient patient = mock(Patient.class);
        when(patientRepository.getReferenceById(2L)).thenReturn(patient);

        ResponseEntity<?> response = patientController.updatePatient(updateDTO);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isInstanceOf(DataUpdatedPatientDTO.class);

        verify(patientRepository, times(1)).getReferenceById(2L);
        verify(patient, times(1)).updateInformation(updateDTO);
    }

    @Test
    void deletePatient_ShouldReturnNoContent() {
        Patient patient = mock(Patient.class);
        when(patientRepository.getReferenceById(3L)).thenReturn(patient);

        ResponseEntity<?> response = patientController.deletePatient(3L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);

        verify(patientRepository, times(1)).getReferenceById(3L);
        verify(patient, times(1)).delete();
    }

    @Test
    void deletePatient_ShouldReturnBadRequest_WhenIdIsNull() {
        ResponseEntity<?> response = patientController.deletePatient(null);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        verifyNoInteractions(patientRepository);
    }
}
