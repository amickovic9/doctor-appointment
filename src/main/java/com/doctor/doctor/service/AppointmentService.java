package com.doctor.doctor.service;

import com.doctor.doctor.domain.Appointment;
import com.doctor.doctor.domain.Doctor;
import com.doctor.doctor.domain.Patient;
import com.doctor.doctor.dto.appointment.CreateAppointmentRequest;
import com.doctor.doctor.enums.AppointmentStatus;
import com.doctor.doctor.exception.DoctorNotFoundException;
import com.doctor.doctor.exception.PatientNotFoundException;
import com.doctor.doctor.handler.AppointmentAlreadyBookedException;
import com.doctor.doctor.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Transactional(readOnly = true)
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Appointment> searchAppointments(String doctorName, String patientName, AppointmentStatus status) {
        if (doctorName != null && patientName != null && status != null) {
            return appointmentRepository.findByDoctorNameAndPatientNameAndStatus(doctorName, patientName, status);
        } else if (doctorName != null && patientName != null) {
            return appointmentRepository.findByDoctorNameAndPatientName(doctorName, patientName);
        } else if (doctorName != null && status != null) {
            return appointmentRepository.findByDoctorNameAndStatus(doctorName, status);
        } else if (patientName != null && status != null) {
            return appointmentRepository.findByPatientNameAndStatus(patientName, status);
        } else if (doctorName != null) {
            return appointmentRepository.findByDoctorName(doctorName);
        } else if (patientName != null) {
            return appointmentRepository.findByPatientName(patientName);
        } else if (status != null) {
            return appointmentRepository.findByStatus(status);
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void createAppointment(CreateAppointmentRequest request) {
            Optional<Doctor> doctorOpt = doctorService.findById(request.getDoctorId());
            if (doctorOpt.isEmpty()) throw new DoctorNotFoundException("Invalid doctor id");
            Doctor doctor = doctorOpt.get();

            Optional<Patient> patientOpt = patientService.findById(request.getPatientId());
            if(patientOpt.isEmpty()) throw new PatientNotFoundException("Invalid patient id");

            LocalDateTime appointmentEndTime = request.getAppointmentTime().plusHours(1);

            List<Appointment> existingAppointments = appointmentRepository.findByDoctorName(doctor.getName());
            for (Appointment a : existingAppointments){
                LocalDateTime start = a.getTime();
                LocalDateTime end = start.plusHours(1).plusMinutes(1);
                if (appointmentEndTime.isAfter(start) & appointmentEndTime.isBefore(end))
                    throw new AppointmentAlreadyBookedException("There is already booked termin for that doctor in that time");
            }

            Patient patient = patientOpt.get();
            Appointment appointment = new Appointment();
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setTime(request.getAppointmentTime());

            appointmentRepository.save(appointment);
    }
}
