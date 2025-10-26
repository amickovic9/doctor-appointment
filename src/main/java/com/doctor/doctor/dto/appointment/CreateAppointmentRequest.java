package com.doctor.doctor.dto.appointment;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CreateAppointmentRequest {

    @NotNull(message = "You need to enter doctor")
    private Integer doctorId;

    @NotNull(message = "You need to enter patient")
    private Integer patientId;

    @NotNull(message = "You need to enter appointment time")
    private LocalDateTime appointmentTime;

    @Override
    public String toString() {
        return "CreateAppointmentRequest{" +
                "doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
