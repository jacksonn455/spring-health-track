package com.springhealthtrack.api.repositories;

import com.springhealthtrack.api.domains.Doctor;
import com.springhealthtrack.api.enums.SpecialtyEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DoctorRepositoryTest {

    @Mock
    private DoctorRepository doctorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllByActiveTrue_ShouldReturnActiveDoctors() {
        Doctor doctor = mock(Doctor.class);
        when(doctor.getId()).thenReturn(1L);
        when(doctor.getName()).thenReturn("Dr. Alice");
        when(doctor.getActive()).thenReturn(true);
        Page<Doctor> page = new PageImpl<>(List.of(doctor));

        when(doctorRepository.findAllByActiveTrue(PageRequest.of(0, 10))).thenReturn(page);

        Page<Doctor> result = doctorRepository.findAllByActiveTrue(PageRequest.of(0, 10));

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Dr. Alice");
        verify(doctorRepository, times(1)).findAllByActiveTrue(PageRequest.of(0, 10));
    }

    @Test
    void chooseRandomDoctorBySpecialty_ShouldReturnDoctor() {
        Doctor doctor = mock(Doctor.class);
        when(doctor.getId()).thenReturn(2L);
        when(doctor.getSpecialty()).thenReturn(SpecialtyEnum.CARDIOLOGY);

        LocalDateTime appointmentDate = LocalDateTime.now();

        when(doctorRepository.chooseRandomDoctorBySpecialty(SpecialtyEnum.CARDIOLOGY, appointmentDate))
                .thenReturn(doctor);

        Doctor result = doctorRepository.chooseRandomDoctorBySpecialty(SpecialtyEnum.CARDIOLOGY, appointmentDate);

        assertThat(result).isNotNull();
        assertThat(result.getSpecialty()).isEqualTo(SpecialtyEnum.CARDIOLOGY);
        verify(doctorRepository, times(1))
                .chooseRandomDoctorBySpecialty(SpecialtyEnum.CARDIOLOGY, appointmentDate);
    }

    @Test
    void findActiveById_ShouldReturnTrueIfActive() {
        when(doctorRepository.findActiveById(1L)).thenReturn(true);

        Boolean isActive = doctorRepository.findActiveById(1L);

        assertThat(isActive).isTrue();
        verify(doctorRepository, times(1)).findActiveById(1L);
    }

    @Test
    void findActiveById_ShouldReturnFalseIfInactive() {
        when(doctorRepository.findActiveById(2L)).thenReturn(false);

        Boolean isActive = doctorRepository.findActiveById(2L);

        assertThat(isActive).isFalse();
        verify(doctorRepository, times(1)).findActiveById(2L);
    }
}
