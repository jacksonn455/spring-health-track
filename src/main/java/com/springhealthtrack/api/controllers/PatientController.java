package com.springhealthtrack.api.controllers;

import com.springhealthtrack.api.domains.Patient;
import com.springhealthtrack.api.dtos.PatientRegistrationDTO;
import com.springhealthtrack.api.dtos.PatientUpdateDTO;
import com.springhealthtrack.api.dtos.ListPatientDTO;
import com.springhealthtrack.api.dtos.DataUpdatedPatientDTO;
import com.springhealthtrack.api.repositories.PatientRepository;
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
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegistrationDTO patient, UriComponentsBuilder uriBuilder) {
        Patient info = new Patient(patient);
        repository.save(info);

        URI uri = uriBuilder.path("/patients/{id}").buildAndExpand(info.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataUpdatedPatientDTO(info));
    }

    @GetMapping
    public ResponseEntity<Page<ListPatientDTO>> getPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<ListPatientDTO> page = repository.findAll(pageable).map(ListPatientDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getPatientById(@PathVariable("id") Long id) {
        Patient patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataUpdatedPatientDTO(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatient(@RequestBody @Valid PatientUpdateDTO data) {
        Patient patient = repository.getReferenceById(data.id());
        patient.updateInformation(data);

        return ResponseEntity.ok(new DataUpdatedPatientDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Patient patient = repository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }
}
