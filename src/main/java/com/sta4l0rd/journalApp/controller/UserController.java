package com.sta4l0rd.journalApp.controller;

import com.sta4l0rd.journalApp.entity.JournalUser;
import com.sta4l0rd.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping
//    public ResponseEntity<?> getAll(){
//        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody JournalUser newJournalUser){
        JournalUser savedEntry = userService.saveUser(newJournalUser);
        if(savedEntry != null){
            return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
