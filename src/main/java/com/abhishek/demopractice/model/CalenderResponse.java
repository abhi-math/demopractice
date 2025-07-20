package com.abhishek.demopractice.model;

import java.util.List;

import lombok.Data;


@Data
public class CalenderResponse {
    
    private String date;
    private boolean hasFreeSlots;
    private List<ScheduleEntry> scheduleEntries;
}
