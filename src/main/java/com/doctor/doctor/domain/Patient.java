package com.doctor.doctor.domain;

import com.doctor.doctor.dto.patient.CreatePatientRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phoneNumber;

    public Patient(CreatePatientRequest request){
        this.name = request.getName();
        this.phoneNumber = request.getPhoneNumber();
        this.email = request.getEmail();
    }
}
