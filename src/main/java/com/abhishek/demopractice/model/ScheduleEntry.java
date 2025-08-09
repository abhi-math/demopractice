package com.abhishek.demopractice.model;

import lombok.Data;

@Data
public class ScheduleEntry {
    private String startTime;
    private String endTime;
    private Integer scheduleEntryId;
}
