package com.doctor.doctor.handler;

public class AppointmentAlreadyBookedException extends RuntimeException{
    public AppointmentAlreadyBookedException(String message){
        super(message);
    }
}
