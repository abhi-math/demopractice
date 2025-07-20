package com.abhishek.demopractice.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.demopractice.model.CalenderResponse;
import com.abhishek.demopractice.model.ScheduleEntry;
import com.abhishek.demopractice.service.CalenderService;

@Service
public class ScheduleAppointmentHelper {

    @Autowired
    private CalenderService calenderService;

    public Integer findScheduleEntryId(String doctor,String appointmentType, String appointmentDate, String startTime) {
    
         List<CalenderResponse> calenderDays= calenderService.callCalender(doctor, appointmentType);
    
        for (CalenderResponse day : calenderDays) {
        if (day.getDate().equals(appointmentDate)) {
            for (ScheduleEntry entry : day.getScheduleEntries()) {
                if (entry.getStartTime().equals(startTime)) {
                    return entry.getScheduleEntryId();
                }
            }
        }
    }
    return null; // or throw exception if preferred
}




}
