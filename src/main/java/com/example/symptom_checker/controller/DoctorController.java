package com.example.symptom_checker.controller;


import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.*;
import com.google.gson.Gson;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @GetMapping
    public String getDoctors() {
        List<Map<String, Object>> doctors = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic", "root", "vinay@vinay7")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM doctors");

            while (rs.next()) {
                Map<String, Object> doctor = new HashMap<>();
                doctor.put("id", rs.getInt("id"));
                doctor.put("name", rs.getString("name"));
                doctor.put("specialty", rs.getString("specialty"));
                doctor.put("experience", rs.getInt("experience"));
                doctors.add(doctor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Gson().toJson(doctors);
    }
}
