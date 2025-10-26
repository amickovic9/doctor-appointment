package com.doctor.doctor.controller;

import com.doctor.doctor.domain.Appointment;
import com.doctor.doctor.dto.appointment.CreateAppointmentRequest;
import com.doctor.doctor.enums.AppointmentStatus;
import com.doctor.doctor.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  com.doctor.doctor.config.RabbitMQConfig;


import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public List<Appointment> findAllAppointments(){
        return appointmentService.findAllAppointments();
    }

    @GetMapping("/search")
    public List<Appointment> search(
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) AppointmentStatus status) {
        return appointmentService.searchAppointments(doctorName, patientName, status);
    }

    @PostMapping
    public void createAppointment(@Valid @RequestBody CreateAppointmentRequest request){
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, request);
    }

}
