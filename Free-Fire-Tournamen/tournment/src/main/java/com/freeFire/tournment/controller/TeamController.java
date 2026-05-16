package com.freeFire.tournment.controller;

import com.freeFire.tournment.model.Team;
import com.freeFire.tournment.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin("*")

public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public void registerTeam(
            Team team,
            HttpServletResponse response
    ) throws Exception {

        teamService.saveTeam(team);

      response.sendRedirect("https://esports-4ae6.onrender.com/payment.html");
    }

    @GetMapping
    public List<Team> getAllTeams() {

        return teamService.getAllTeams();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadScreenshot(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        String uploadDir =
                System.getProperty("user.dir")
                        + "/uploads/";

        File directory = new File(uploadDir);

        if (!directory.exists()) {

            directory.mkdirs();
        }

        String filePath =
                uploadDir
                        + file.getOriginalFilename();

        file.transferTo(new File(filePath));

        return ResponseEntity.ok(
                "Screenshot Uploaded Successfully"
        );
    }

    @PostMapping("/api/contact/send-inquiry")
    public ResponseEntity<String> sendContactInquiry(
            @RequestBody Map<String, String> contactData
    ) {
        try {
            String name = contactData.get("name");
            String phone = contactData.get("phone");
            String message = contactData.get("message");

            String emailBody = "New Contact Inquiry:\n\n" +
                    "Name: " + name + "\n" +
                    "Phone: " + phone + "\n" +
                    "Message: " + message;

            teamService.sendContactEmail(emailBody);

            return ResponseEntity.ok("Inquiry sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending inquiry: " + e.getMessage());
        }
    }
}
