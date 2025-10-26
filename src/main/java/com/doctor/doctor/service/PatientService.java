package com.doctor.doctor.service;

import com.doctor.doctor.domain.Doctor;
import com.doctor.doctor.domain.Patient;
import com.doctor.doctor.dto.patient.CreatePatientRequest;
import com.doctor.doctor.exception.EmailAlreadyUsedException;
import com.doctor.doctor.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Transactional(readOnly = true)
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Transactional
    public void createPatient(CreatePatientRequest request) {
        if(patientRepository.existsByEmail(request.getEmail())) {
            logger.info("Trying to create patient with mail " + request.getEmail()) ;
            throw new EmailAlreadyUsedException("There is already patient with That mail");
        }
        Patient patient = new Patient(request);
        patientRepository.save(patient);
        logger.info("Crated patient " + request);
    }

    @Transactional(readOnly = true)
    public List<Patient> findPatient(String name, String email) {
        logger.info("Search patient by name and email " + name + " " + email);
        if (name != null && email != null) {
            return patientRepository.findByNameAndEmail(name, email);
        } else if (name != null) {
            return patientRepository.findByName(name);
        } else if (email != null) {
            return patientRepository.findByEmail(email);
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional(readOnly = true)
    public Optional<Patient> findById(Integer id){
        logger.info("Search patient by id " + id);
        return patientRepository.findById(id);
    }
}
