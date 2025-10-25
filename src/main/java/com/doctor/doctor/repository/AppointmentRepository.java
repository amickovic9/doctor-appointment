package com.doctor.doctor.repository;

import com.doctor.doctor.domain.Appointment;
import com.doctor.doctor.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctorNameAndPatientNameAndStatus(String doctorName, String patientName, AppointmentStatus status);

    List<Appointment> findByDoctorNameAndPatientName(String doctorName, String patientName);

    List<Appointment> findByDoctorNameAndStatus(String doctorName, AppointmentStatus status);

    List<Appointment> findByPatientNameAndStatus(String patientName, AppointmentStatus status);

    List<Appointment> findByDoctorName(String doctorName);

    List<Appointment> findByPatientName(String patientName);

    List<Appointment> findByStatus(AppointmentStatus status);
}
