package com.sta4l0rd.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {
    @GetMapping("/health-check")
    public String heathCheck(){
        return "OK";
    }
}
