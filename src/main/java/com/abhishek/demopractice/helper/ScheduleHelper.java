package com.abhishek.demopractice.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.demopractice.model.ScheduleResponse;
import com.abhishek.demopractice.service.WebClientService;

@Service
public class ScheduleHelper {

    @Autowired
    private WebClientService webClientService;

    public ScheduleResponse findSchedule(String doctorName, String appointmentName) {
        try {
            String key = doctorName.toLowerCase().replaceAll("\\s+", "") + "|" + appointmentName.toLowerCase().replaceAll("\\s+", "");
            System.out.println("key:"+key);
            ScheduleResponse result = webClientService.getScheduleMap().get(key);
            System.out.println("scheduleResponse: "+ result);

            if (result == null) {
                System.out.println("No match found for doctor: " + doctorName + " and appointment: " + appointmentName);
            }

            return result;
        } catch (Exception e) {
            System.err.println("Error in findSchedule: " + e.getMessage());
            return null;
        }
    }

}
