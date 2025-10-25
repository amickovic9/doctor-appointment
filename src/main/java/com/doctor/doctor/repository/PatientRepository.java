package com.doctor.doctor.repository;

import com.doctor.doctor.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByNameAndEmail(String name, String email);

    List<Patient> findByName(String name);

    List<Patient> findByEmail(String email);

    boolean existsByEmail(String email);
}
