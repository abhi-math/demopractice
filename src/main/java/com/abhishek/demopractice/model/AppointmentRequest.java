package com.abhishek.demopractice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AppointmentRequest {
    @NotBlank(message = "Doctor is required")
    private String doctor;

    @NotBlank(message = "Appointment type is required")
    private String appointmentType;

    @NotBlank(message = "Appointment date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Appointment date must be in YYYY-MM-DD format")
    private String appointmentDate;

    @NotBlank(message = "Start time is required")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "Start time must be in HH:mm:ss format")
    private String startTime;


    @NotBlank(message = "End time is required")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "End time must be in HH:mm:ss format")
    private String endTime;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in YYYY-MM-DD format")
    private String dateOfBirth;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+1\\d{10}$", message = "Phone number must start with +1 and be followed by 10 digits")
    private String phoneNumber;

    @Min(value = 1, message = "Schedule entry ID must be greater than 0")
    private String scheduleEntryId;

    public void setPhoneNumber(String phoneNumber) {
    if (phoneNumber != null) {
        phoneNumber = phoneNumber.trim();
        // If only 10 digits provided, prepend +1
        if (phoneNumber.matches("^\\d{10}$")) {
            this.phoneNumber = "+1" + phoneNumber;
            return;
        }
    }
    this.phoneNumber = phoneNumber;
    }

}
