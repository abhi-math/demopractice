package com.abhishek.demopractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.demopractice.helper.ScheduleHelper;
import com.abhishek.demopractice.model.CalenderResponse;
import com.abhishek.demopractice.model.ScheduleResponse;


import java.util.List;


@Service
public class CalenderService {

    @Autowired
    private ScheduleHelper scheduleHelper;

    @Autowired
    private WebClientService webClientService;

    public List<CalenderResponse> callCalender(String doctorName, String appointmentName) {
        try {
            // Clean and retrieve schedule
            ScheduleResponse scheduleResponse = scheduleHelper.findSchedule(doctorName, appointmentName);
            System.out.println("scheduleResponse: " + scheduleResponse);

            if (scheduleResponse == null) {
                throw new RuntimeException("No schedule found for the given doctor and appointment type.");
            }

            try {
                
                List<CalenderResponse> calendarResponses = webClientService.getCalendarFreeDays(scheduleResponse);
                return calendarResponses;
            } catch (Exception e) {
                System.err.println("Error in callCalender: " + e.getMessage());
                throw new RuntimeException("Internal error in calendar service.");
            }
        
        } catch (Exception e) {
            System.err.println("Error in callCalender: " + e.getMessage());
            throw new RuntimeException("Internal error in calendar service.");
        }
    }
}
