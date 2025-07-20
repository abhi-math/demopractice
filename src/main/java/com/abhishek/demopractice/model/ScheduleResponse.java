package com.abhishek.demopractice.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter

public class ScheduleResponse {
    private AppointmentType appointmentType;
    private Doctor doctor;
    private int roomId;
    private int scheduleEntryId;

}
