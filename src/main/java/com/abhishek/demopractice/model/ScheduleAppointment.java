package com.abhishek.demopractice.model;

import lombok.Data;

@Data
public class ScheduleAppointment {
    private String scheduleEntryId; /* 91 */
    private String appointmentDate; /* "2025-07-08" */
    private String startTime; /* "14:00:00" */
    private String endTime; /* "14:30:00" */
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth; /* "1999-05-01" */
    private String phoneNumber; /* "+13695215731" */
    private final String verificationToken = null;

}
