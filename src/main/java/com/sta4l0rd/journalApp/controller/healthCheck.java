package com.sta4l0rd.journalApp.controller;

import org.bson.json.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {
    @GetMapping("/public/health")
    public ResponseEntity<?> heathCheck(){
        return new ResponseEntity<>("Server is running",HttpStatus.OK);
    }
}
