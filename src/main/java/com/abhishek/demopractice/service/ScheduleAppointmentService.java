package com.abhishek.demopractice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.demopractice.helper.ScheduleAppointmentHelper;

@Service
public class ScheduleAppointmentService {

    @Autowired
    private WebClientService webClientService;

    @Autowired
    private ScheduleAppointmentHelper scheduleAppointmentHelper;

    public Map<String, Object> doScheduleAppointment(String doctor,
            String appointmentType,
            String appoitnmentDate,
            String startTime,
            String endTime, String firstName, String lastName, String email, String dateOfBirth, String phoneNumber) {

        
        Integer scheduleEntryId = scheduleAppointmentHelper.findScheduleEntryId(doctor, appointmentType,
                appoitnmentDate, startTime);

        if (scheduleEntryId==-1){
            System.out.println("incorrect schedluleEntryId:");
            System.out.println("doctor: "+doctor);
            System.out.println("appointmentType: "+appointmentType);
            System.out.println("appoitnmentDate: "+appoitnmentDate);
            System.out.println("startTime: "+startTime);
            return Map.of("error","incorrect input could not find any scheduleEntryId");
        }

        // find scheduleEntryId from appointmentDate and startTime

        return webClientService.doSchedule(scheduleEntryId, appoitnmentDate, startTime, endTime,  firstName,lastName, email, dateOfBirth, phoneNumber);

    }

}
