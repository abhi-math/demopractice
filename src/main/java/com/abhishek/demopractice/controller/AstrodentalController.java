package com.abhishek.demopractice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.demopractice.model.CalenderResponse;
import com.abhishek.demopractice.service.CalenderService;
import com.abhishek.demopractice.service.ScheduleAppointmentService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/")
public class AstrodentalController {

    @Autowired
    private CalenderService calenderService;

   

    @Autowired
    private ScheduleAppointmentService scheduleAppointmentService;

    

    @GetMapping("doGetCalendarFreeDays/{doctor}/{appointmentType}")
    public ResponseEntity<List<CalenderResponse>> doGetCalendarFreeDays(@PathVariable String doctor,@PathVariable String appointmentType) {
        System.out.println("calender request: "+ doctor+" "+ appointmentType);
        // System.out.println("calender response"+calenderService.callCalender(doctor, appointmentType));
        
     
        return ResponseEntity.ok(calenderService.callCalender(doctor, appointmentType));

    }


    @GetMapping("bookAppointment/{doctor}/{appointmentType}/{appoitnmentDate}/{startTime}/{endTime}/{firstName}/{lastName}/{email}/{dateOfBirth}/{phoneNumber}")
    public  String bookAppointment(@PathVariable String doctor,
        @PathVariable String appointmentType,
        @PathVariable String appoitnmentDate,
        @PathVariable String startTime,
        @PathVariable String endTime,

        @PathVariable String firstName,
        @PathVariable String lastName,
        @PathVariable String email,
        @PathVariable String dateOfBirth,
        @PathVariable String phoneNumber) {
        //TODO: process POST request

        //check the the data passing to the uri through UI 
        //make a rough webclient to pass with map.of to understand the data structure
        //design the receptist with all booking details
        //see the data coming
        //make a service class accordingly like calenderservice if required a helper class also
        //clean the data according to the url api
        //send it through

        // String doctor="Veerjinder Singh";
        // String appointmentType="Emergency Exam";
        // String appoitnmentDate= "2025-07-24";
        // String startTime="09:00:00";
        // String endTime="09:30:00";

        // String firstName="test";
        // String lastName="last";
        // String email="test@gmail.com";
        // String dateOfBirth="1999-02-02"; 
        // String phoneNumber="+11235157654";

        System.out.println("bookAppointment requested : doctor:"+doctor+" appointmentType:"+appointmentType);
        System.out.println("appoitnmentDate:"+appoitnmentDate+" startTime:"+ startTime+" endTime:"+endTime);
        System.out.println("firstName:"+ firstName+" lastName"+lastName+" email:"+email+" dateOfBirth:"+dateOfBirth+" phoneNumber:"+phoneNumber);

        
        try {
            
           return scheduleAppointmentService.doScheduleAppointment(doctor,appointmentType,appoitnmentDate,startTime,endTime,  firstName,  lastName,  email,  dateOfBirth,  phoneNumber).get("id").toString();
             
            
        } catch (Exception e) {
            System.out.println("unable to schedule: "+e.getMessage());
            
        }
        
        return new String("Hello");
    }
    

    
}
