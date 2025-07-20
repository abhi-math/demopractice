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

        // call this from controller with the body

        // String doctor="Veerjinder Singh";
        // String appointmentType="Emergency Exam";
        // String appoitnmentDate= "2025-07-24";
        // String startTime="09:00:00";
        // String endTime="09:30:00";
        Integer scheduleEntryId = scheduleAppointmentHelper.findScheduleEntryId(doctor, appointmentType,
                appoitnmentDate, startTime);

        // find scheduleEntryId from appointmentDate and startTime

        return webClientService.doSchedule(scheduleEntryId, appoitnmentDate, startTime, endTime,  firstName,lastName, email, dateOfBirth, phoneNumber);

    }

}
