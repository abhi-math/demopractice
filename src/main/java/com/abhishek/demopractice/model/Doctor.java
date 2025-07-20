package com.abhishek.demopractice.model;

import lombok.Data;

@Data
public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String fullName;
    private boolean showDoctorPrefix;

}
