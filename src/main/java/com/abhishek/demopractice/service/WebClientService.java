package com.abhishek.demopractice.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.abhishek.demopractice.model.CalenderResponse;
import com.abhishek.demopractice.model.ScheduleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.Data;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class WebClientService {

    private final WebClient webClient;

    private Map<String, ScheduleResponse> scheduleMap;

    WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    @PostConstruct
    public void getScheduleTypes() {
        try {
            ScheduleResponse[] responseArray = webClient.get()
                    .uri("doGetScheduleTypes?locationId=1")
                    .retrieve()
                    .bodyToMono(ScheduleResponse[].class)
                    .doOnError(e -> System.err.println("WebClient error in getScheduleTypes: " + e.getMessage()))
                    .onErrorReturn(new ScheduleResponse[0])
                    .block(); // Block only once

            System.out.println("schedule response:" + Arrays.toString(responseArray));

            scheduleMap = new HashMap<>();
            if (responseArray != null) {
                for (ScheduleResponse appt : responseArray) {
                    if (appt.getDoctor() != null && appt.getAppointmentType() != null) {
                        String key = appt.getDoctor().getFullName().toLowerCase().replaceAll("\\s+", "") + "|" +
                                appt.getAppointmentType().getName().toLowerCase().replaceAll("\\s+", "");
                        scheduleMap.put(key, appt);
                    } else {
                        System.err.println("Incomplete ScheduleResponse: " + appt);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in getScheduleTypes: " + e.getMessage());
            scheduleMap = new HashMap<>(); // ensure it's initialized to avoid NullPointerException later
        }
    }

    public List<CalenderResponse> getCalendarFreeDays(ScheduleResponse scheduleResponse) {
        try {
            Map<String, Object> requestBody = Map.of(
                    "from", LocalDate.now().toString(),
                    "to", LocalDate.now().plusMonths(2).toString(),
                    "locationId", 1,
                    "appointmentTypeId", scheduleResponse.getAppointmentType().getId(),
                    "roomId", scheduleResponse.getRoomId(),
                    "doctorId", scheduleResponse.getDoctor().getId()
            // "from", "2025-07-08",
            // "appointmentTypeId", 3,
            // "locationId", 1,
            // "roomId", 16,
            // "doctorId", 21,
            // "to", "2025-09-08"
            );

            System.out.println("requestbody: " + requestBody);

            return webClient.post()
                    .uri("doGetCalendarFreeDays")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .header("TimeZone", "Asia/Calcutta")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToFlux(CalenderResponse.class)
                    .collectList() // ✅ collect to List<CalenderResponse>
                    .doOnError(e -> System.err.println("WebClient error: " + e.getMessage()))
                    .onErrorReturn(Collections.emptyList())
                    .block();

        } catch (Exception e) {
            // Handle synchronous errors, such as null pointer in scheduleResponse
            System.err.println("Exception in fetchScheduleDays: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Map<String, Object> doSchedule(Integer scheduleId, String appoitnmentDate, String startTime, String endTime,
            String firstName, String lastName, String email, String dateOfBirth, String phoneNumber) {// Call is
                                                                                                      // successfull
                                                                                                      // returns id
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("scheduleEntryId", scheduleId);
            requestBody.put("appointmentDate", appoitnmentDate);
            requestBody.put("startTime", startTime);
            requestBody.put("endTime", endTime);
            requestBody.put("firstName", firstName);
            requestBody.put("lastName", lastName);// "last"
            requestBody.put("email", email);// "test@gmail.com"
            requestBody.put("dateOfBirth", dateOfBirth);// "2025-07-01"
            requestBody.put("phoneNumber", phoneNumber); // "+11235157654"
            requestBody.put("verificationToken", null);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBodyJson = objectMapper.writeValueAsString(requestBody);

            System.out.println("schedule requestBody: " + requestBodyJson);

            return webClient.post()
                    .uri("doScheduleAppointment")
                    .header(HttpHeaders.ACCEPT, "application/json")
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header("TimeZone", "Asia/Calcutta")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .block();
        } catch (Exception e) {
            System.err.println("Error during schedule appointment: " + e.getMessage());
            e.printStackTrace();
            return Map.of("error", "Failed to schedule appointment");
        }
    }

}
