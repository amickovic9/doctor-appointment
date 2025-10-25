package com.doctor.doctor.domain;

import com.doctor.doctor.dto.doctor.CreateDoctorRequest;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phoneNumber;

    public Doctor(CreateDoctorRequest request){
        this.name = request.getName();
        this.phoneNumber = request.getPhoneNumber();
        this.email = request.getEmail();
    }
}
