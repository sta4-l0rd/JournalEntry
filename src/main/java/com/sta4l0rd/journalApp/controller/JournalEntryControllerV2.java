package com.sta4l0rd.journalApp.controller;

import com.sta4l0rd.journalApp.entity.JournalEntry;
import com.sta4l0rd.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal/v2")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAllEntries() {
        return new ResponseEntity<>(journalEntryService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry newEntry) {
        JournalEntry savedEntry = journalEntryService.saveEntry(newEntry);
        if(savedEntry != null){
            return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{searchId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId searchId) {
        JournalEntry foundEntry = journalEntryService.findById(searchId);
        if(foundEntry != null){
            return new ResponseEntity<JournalEntry>(foundEntry, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{searchId}")
    public ResponseEntity<?> replaceJournalEntryById(@PathVariable ObjectId searchId, @RequestBody JournalEntry newEntry){
        JournalEntry replacedEntry = journalEntryService.replaceById(searchId, newEntry);
        if(replacedEntry != null){
            return new ResponseEntity<>(replacedEntry, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/id/{searchId}")
    public ResponseEntity<?> modifyJournalEntryById (@PathVariable ObjectId searchId, @RequestBody JournalEntry newEntry){
        JournalEntry modifiedEntry = journalEntryService.modifyById(searchId, newEntry);
        if(modifiedEntry != null){
            return new ResponseEntity<>(modifiedEntry, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{searchId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId searchId) {
        if(journalEntryService.deleteById(searchId) == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
