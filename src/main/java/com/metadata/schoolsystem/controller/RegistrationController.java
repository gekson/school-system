package com.metadata.schoolsystem.controller;

import com.metadata.schoolsystem.model.Registration;
import com.metadata.schoolsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/registerStudent")
    public ResponseEntity registerStudent(@RequestBody Registration registration) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.registrationService.registerStudent(registration));
    }
}
