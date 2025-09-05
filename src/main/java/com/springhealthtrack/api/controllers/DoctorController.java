package com.springhealthtrack.api.controllers;

import com.springhealthtrack.api.dtos.DataUpdatedDoctorDTO;
import com.springhealthtrack.api.dtos.DoctorRegistrationDTO;
import com.springhealthtrack.api.dtos.DoctorUpdateDTO;
import com.springhealthtrack.api.dtos.ListDoctorDTO;
import com.springhealthtrack.api.domains.Doctor;
import com.springhealthtrack.api.repositories.DoctorRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorRegistrationDTO doctor, UriComponentsBuilder uriBuilder) {
        Doctor info = new Doctor(doctor);
        repository.save(info);

        URI uri = uriBuilder.path("/doctors/{id}").buildAndExpand(info.id).toUri();
        return ResponseEntity.created(uri).body(new DataUpdatedDoctorDTO(info));
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorDTO>> getDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<ListDoctorDTO> page = repository.findAllByActiveTrue(pageable).map(ListDoctorDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getDoctorById(@PathVariable("id") Long id) {
        Doctor doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataUpdatedDoctorDTO(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorUpdateDTO data) {
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.updateInformation(data);

        return ResponseEntity.ok(new DataUpdatedDoctorDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Doctor doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }
}