package com.doctor.doctor.controller;

import com.doctor.doctor.domain.Patient;
import com.doctor.doctor.dto.patient.CreatePatientRequest;
import com.doctor.doctor.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> findAllPatients(){
        return patientService.findAll();
    }

    @PostMapping
    public void createPatient(@Valid @RequestBody CreatePatientRequest request){
        patientService.createPatient(request);
    }

    @GetMapping("/search")
    public List<Patient> findPatient(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return patientService.findPatient(name, email);
    }
}
