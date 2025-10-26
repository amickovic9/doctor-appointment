package com.doctor.doctor.service;

import com.doctor.doctor.domain.Doctor;
import com.doctor.doctor.dto.doctor.CreateDoctorRequest;
import com.doctor.doctor.exception.EmailAlreadyUsedException;
import com.doctor.doctor.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Transactional(readOnly = true)
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    @Transactional
    public void createDoctor(CreateDoctorRequest request) {
        if(doctorRepository.existsByEmail(request.getEmail())) {
            logger.info("Email already exists " + request.getEmail());
            throw new EmailAlreadyUsedException("There is already doctor with that email");
        }
        Doctor doctor = new Doctor(request);
        doctorRepository.save(doctor);
        logger.info("Created Doctor: " + request);

    }

    @Transactional(readOnly = true)
    public List<Doctor> findDoctorByName(String name) {
        logger.info("Search by name" +  name);
        return doctorRepository.findByName(name);
    }

    public List<Doctor> findDoctor(String name, String email) {
        logger.info("Search by name and email" + name + " " + email);
        if(name != null & email != null){
            return doctorRepository.findByNameAndEmail(name, email);
        }
        else if(name != null) return doctorRepository.findByName(name);
        else if(email != null) return doctorRepository.findByEmail(email);
        return Collections.emptyList();
    }

    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Integer id){
        logger.info("Search by id " + id);
        return doctorRepository.findById(id);
    }
}
