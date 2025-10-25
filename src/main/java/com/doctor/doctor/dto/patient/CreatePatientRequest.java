package com.doctor.doctor.dto.patient;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreatePatientRequest {
    @NotBlank(message = "You must provide a patient name")
    private String name;

    @NotBlank(message = "You must provide a patient naemailme")
    private String email;

    @NotBlank(message = "You most provide a phone number for patient")
    private String phoneNumber;
}
