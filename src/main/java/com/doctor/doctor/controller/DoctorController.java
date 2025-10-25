package com.doctor.doctor.controller;

import com.doctor.doctor.domain.Doctor;
import com.doctor.doctor.dto.doctor.CreateDoctorRequest;
import com.doctor.doctor.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public void createDoctor(@Valid @RequestBody CreateDoctorRequest request)
    {
        doctorService.createDoctor(request);
    }

    @GetMapping("/search")
    public List<Doctor> findDoctorByName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    )
    {
        return doctorService.findDoctor(name,email);
    }
}
