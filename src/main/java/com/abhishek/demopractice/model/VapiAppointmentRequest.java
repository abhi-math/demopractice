package com.abhishek.demopractice.model;

import lombok.Data;

@Data
public class VapiAppointmentRequest {
    private String doctor;
    private String appointmentType;
    private String appointmentDate;
    private String startTime;
    private String endTime;

    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String phoneNumber;
    private String scheduleEntryId;

}
