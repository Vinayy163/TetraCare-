package com.example.symptom_checker.controller;

import org.springframework.web.bind.annotation.*;
import java.sql.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @PostMapping
    public String bookAppointment(@RequestParam String doctorId,
                                  @RequestParam String name,
                                  @RequestParam String email,
                                  @RequestParam String date) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/clinic";
        String dbUser = "root";
        String dbPass = "vinay@vinay7";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
                String sql = "INSERT INTO appointments (doctor_id, name, email, date) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, Integer.parseInt(doctorId));
                    pst.setString(2, name);
                    pst.setString(3, email);
                    pst.setString(4, date);

                    int rows = pst.executeUpdate();
                    if (rows > 0) {
                        return "success";
                    } else {
                        return "failed";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error: " + e.getMessage();
        }
    }
}
