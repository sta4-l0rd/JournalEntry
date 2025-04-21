package com.sta4l0rd.journalApp.controller;

import com.sta4l0rd.journalApp.entity.JournalEntry;
import com.sta4l0rd.journalApp.service.JournalEntryService;
import com.sta4l0rd.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    private String getAuthenticatedName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping
    public ResponseEntity<?> readAllJournalEntries(){
        List<JournalEntry> entries = journalEntryService.readAllEntries(getAuthenticatedName());
        if(entries != null){
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/entry/{searchId}")
    public ResponseEntity<?> readJournalEntryById(@PathVariable ObjectId searchId) {
        JournalEntry foundEntry = journalEntryService.findById(searchId, getAuthenticatedName());
        if(foundEntry != null){
            return new ResponseEntity<JournalEntry>(foundEntry, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntry newEntry) {
        JournalEntry savedEntry = journalEntryService.createEntry(newEntry, getAuthenticatedName());
        if(savedEntry != null){
            return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/entry/{searchId}")
    public ResponseEntity<?> replaceJournalEntryById(@PathVariable ObjectId searchId, @RequestBody JournalEntry newEntry){
        JournalEntry replacedEntry = journalEntryService.replaceById(searchId, newEntry, getAuthenticatedName());
        if(replacedEntry != null){
            return new ResponseEntity<>(replacedEntry, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/entry/{searchId}")
    public ResponseEntity<?> modifyJournalEntryById (@PathVariable ObjectId searchId, @RequestBody JournalEntry newEntry){
        JournalEntry modifiedEntry = journalEntryService.modifyById(searchId, newEntry, getAuthenticatedName());
        if(modifiedEntry != null){
            return new ResponseEntity<>(modifiedEntry, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/entry/{searchId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId searchId) {
        if(journalEntryService.deleteById(searchId, getAuthenticatedName()) == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
