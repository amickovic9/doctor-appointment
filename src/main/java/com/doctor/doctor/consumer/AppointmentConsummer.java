package com.doctor.doctor.consumer;

import com.doctor.doctor.config.RabbitMQConfig;
import com.doctor.doctor.dto.appointment.CreateAppointmentRequest;
import com.doctor.doctor.exception.DoctorNotFoundException;
import com.doctor.doctor.exception.PatientNotFoundException;
import com.doctor.doctor.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class AppointmentConsummer {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentConsummer.class);

    @Autowired
    private AppointmentService appointmentService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void processAppointment(CreateAppointmentRequest request) {
        try {
            appointmentService.createAppointment(request);
            logger.info("Created appointment " + request);
        } catch (DoctorNotFoundException | PatientNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

}
