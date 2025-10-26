package com.doctor.doctor.dto.doctor;
import jakarta.validation.constraints.NotBlank;


import lombok.Getter;

@Getter
public class CreateDoctorRequest {
    @NotBlank(message = "You must provide a doctor name")
    private String name;

    @NotBlank(message = "You must provide a doctor email")
    private String email;

    @NotBlank(message = "You must provide a doctor phone number")
    private String phoneNumber;

    @Override
    public String toString() {
        return "CreateDoctorRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}


