package com.abhishek.demopractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UiCheckController {

    @GetMapping
    public String getFirstPage(){
        return new String("Hello!🙂 The site is working perfectly.\n\n fetch through apis \n api/doGetCalendarFreeDays/{doctor}/{appointmentType} \n api/bookAppointment/{doctor}/{appointmentType}/{appoitnmentDate}/{startTime}/{endTime}/{firstName}/{lastName}/{email}/{dateOfBirth}/{phoneNumber} "); 
    }


}
