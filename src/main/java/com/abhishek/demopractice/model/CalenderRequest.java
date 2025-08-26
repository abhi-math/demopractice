package com.abhishek.demopractice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CalenderRequest {
    @NotBlank(message = "Doctor is required")
    private String doctor;

    @NotBlank(message = "Appointment type is required")
    private String appointmentType;
}
