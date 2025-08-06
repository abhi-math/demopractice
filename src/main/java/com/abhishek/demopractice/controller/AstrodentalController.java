package com.abhishek.demopractice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.demopractice.model.CalenderResponse;
import com.abhishek.demopractice.model.VapiAppointmentRequest;
import com.abhishek.demopractice.model.VapiCalenderRequest;
import com.abhishek.demopractice.service.CalenderService;
import com.abhishek.demopractice.service.ScheduleAppointmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/")
// @CrossOrigin(origins = "https://a8489e398972.ngrok-free.appp", allowedHeaders = "*", methods = { RequestMethod.GET,
//         RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
public class AstrodentalController {

    @Autowired
    private CalenderService calenderService;

    @Autowired
    private ScheduleAppointmentService scheduleAppointmentService;

    @GetMapping("doGetCalendarFreeDays/{doctor}/{appointmentType}")
    public ResponseEntity<List<CalenderResponse>> doGetCalendarFreeDays(@PathVariable String doctor,
            @PathVariable String appointmentType) {
        System.out.println("calender request: " + doctor + " " + appointmentType);
        

        return ResponseEntity.ok(calenderService.callCalender(doctor, appointmentType));

    }

    @GetMapping("bookAppointment/{doctor}/{appointmentType}/{appoitnmentDate}/{startTime}/{endTime}/{firstName}/{lastName}/{email}/{dateOfBirth}/{phoneNumber}")
    public String bookAppointment(@PathVariable String doctor,
            @PathVariable String appointmentType,
            @PathVariable String appoitnmentDate,
            @PathVariable String startTime,
            @PathVariable String endTime,

            @PathVariable String firstName,
            @PathVariable String lastName,
            @PathVariable String email,
            @PathVariable String dateOfBirth,
            @PathVariable String phoneNumber) {
        // TODO: process POST request

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

        System.out.println("bookAppointment requested : doctor:" + doctor + " appointmentType:" + appointmentType);
        System.out.println("appoitnmentDate:" + appoitnmentDate + " startTime:" + startTime + " endTime:" + endTime);
        System.out.println("firstName:" + firstName + " lastName" + lastName + " email:" + email + " dateOfBirth:"
                + dateOfBirth + " phoneNumber:" + phoneNumber);

        try {

            return scheduleAppointmentService.doScheduleAppointment(doctor, appointmentType, appoitnmentDate, startTime,
                    endTime, firstName, lastName, email, dateOfBirth, phoneNumber).get("id").toString();

        } catch (Exception e) {
            System.out.println("unable to schedule: " + e.getMessage());

        }

        return new String("Hello");
    }

    @PostMapping("doGetVapiCalendarFreeDays")
    public ResponseEntity<List<CalenderResponse>> doGetVapiCalendarFreeDays(
            @RequestBody VapiCalenderRequest vapiCalenderRequest) {
        System.out.println("calender request: " + vapiCalenderRequest.getDoctor() + " "
                + vapiCalenderRequest.getAppointmentType());
        return ResponseEntity.ok(calenderService.callCalender(vapiCalenderRequest.getDoctor(),
                vapiCalenderRequest.getAppointmentType()));

    }

    @PostMapping("doVapiBookAppointment")
    public Map<String,String> doPostVapiBookAppointment(@RequestBody VapiAppointmentRequest vapiAppointmentRequest) {
        System.out.println("doPostVapiBookAppointment requested: " + vapiAppointmentRequest);

        try {

            String id= scheduleAppointmentService.doScheduleAppointment(vapiAppointmentRequest.getDoctor(),vapiAppointmentRequest.getAppointmentType(),vapiAppointmentRequest.getAppointmentDate(),vapiAppointmentRequest.getStartTime(),
                   vapiAppointmentRequest.getEndTime(),vapiAppointmentRequest.getFirstName(),vapiAppointmentRequest.getLastName(),vapiAppointmentRequest.getEmail(),vapiAppointmentRequest.getDateOfBirth(),vapiAppointmentRequest.getPhoneNumber()).get("id").toString();
            Map<String,String> map=new HashMap<>();
            map.put("id", id);
            return map;
        
        
        } catch (Exception e) {
            System.out.println("unable to schedule: " + e.getMessage());

        }

        Map<String,String> map=new HashMap<>();
        map.put("id", new String("could not find id"));
        return map;

    }
    

}

