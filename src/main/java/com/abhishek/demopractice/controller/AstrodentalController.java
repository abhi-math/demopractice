package com.abhishek.demopractice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.demopractice.model.CalenderResponse;
import com.abhishek.demopractice.model.AppointmentRequest;
import com.abhishek.demopractice.model.CalenderRequest;
import com.abhishek.demopractice.service.CalenderService;
import com.abhishek.demopractice.service.ScheduleAppointmentService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/")
public class AstrodentalController {

    @Autowired
    private CalenderService calenderService;

    @Autowired
    private ScheduleAppointmentService scheduleAppointmentService;

    
    @PostMapping("doGetCalendarFreeDays")
    public ResponseEntity<List<CalenderResponse>> doGetCalendarFreeDays(@Valid @RequestBody CalenderRequest vapiCalenderRequest) {
        System.out.println("calender request: " + vapiCalenderRequest.getDoctor() + " "
                + vapiCalenderRequest.getAppointmentType());
        try {
             List<CalenderResponse> calenderResponses = calenderService.callCalender(vapiCalenderRequest.getDoctor(),vapiCalenderRequest.getAppointmentType());   
            if(!calenderResponses.isEmpty()){
                return ResponseEntity.ok(calenderResponses);
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            System.err.println("Error CalendarFreeDays: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
       
    }

    @PostMapping("doBookAppointment")
    public ResponseEntity<Map<String,String>> doPostBookAppointment(@Valid @RequestBody AppointmentRequest vapiAppointmentRequest) {
        System.out.println("doPostVapiBookAppointment requested: " + vapiAppointmentRequest);

        try {

            String id= scheduleAppointmentService.doScheduleAppointment(vapiAppointmentRequest.getDoctor(),vapiAppointmentRequest.getAppointmentType(),vapiAppointmentRequest.getAppointmentDate(),vapiAppointmentRequest.getStartTime(),
                   vapiAppointmentRequest.getEndTime(),vapiAppointmentRequest.getFirstName(),vapiAppointmentRequest.getLastName(),vapiAppointmentRequest.getEmail(),vapiAppointmentRequest.getDateOfBirth(),vapiAppointmentRequest.getPhoneNumber()).get("id").toString();
            if (!id.isEmpty()) { 
                Map<String,String> map=new HashMap<>();
                map.put("id", id);
                return ResponseEntity.ok(map);
            }
            return ResponseEntity.internalServerError().build();
        
        
        } catch (Exception e) {
            System.out.println("unable to schedule: " + e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }
    
}

