package com.sta4l0rd.journalApp.controller;

import com.sta4l0rd.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAllEntries(){
        return new ArrayList<JournalEntry>(journalEntries.values());
    }
    @PostMapping
    public void createEntry(@RequestBody JournalEntry newEntry){
        journalEntries.put(newEntry.getId(), newEntry);
    }
    @GetMapping("/id/{searchId}")
    public JournalEntry getJournalEntryById(@PathVariable Long searchId){
        return journalEntries.get(searchId);
    }
    @DeleteMapping("/id/{searchId}")
    public int deleteJournalEntryById(@PathVariable Long searchId){
        journalEntries.remove(searchId);
        return 0;
    }
}
