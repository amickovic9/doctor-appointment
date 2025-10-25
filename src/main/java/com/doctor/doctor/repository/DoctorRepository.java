package com.doctor.doctor.repository;

import com.doctor.doctor.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByName(String name);

    List<Doctor> findByNameAndEmail(String name, String email);

    List<Doctor> findByEmail(String email);

    boolean existsByEmail(String email);
}
