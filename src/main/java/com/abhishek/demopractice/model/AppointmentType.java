package com.abhishek.demopractice.model;

import lombok.Data;

@Data
public class AppointmentType {
    private int id;
    private String name;
    private final Integer appointmentLength=30;

}
