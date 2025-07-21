package com.abhishek.demopractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UiCheckController {

    @GetMapping(value = "/", produces = "text/html")
    public String getFirstPage() {
        return """
                <html>
                    <body>
                        <h2>Hello! 🙂</h2>
                        <p>The site is working perfectly.</p>
                        <br>
                        <br>
                        <p><strong>Fetch through APIs:</strong></p>
                        <ul>
                            <li>/api/doGetCalendarFreeDays/{doctor}/{appointmentType}</li>
                            <li>/api/bookAppointment/{doctor}/{appointmentType}/{appointmentDate}/{startTime}/{endTime}/{firstName}/{lastName}/{email}/{dateOfBirth}/{phoneNumber}</li>
                        </ul>
                    </body>
                </html>
                """;
    }

}
