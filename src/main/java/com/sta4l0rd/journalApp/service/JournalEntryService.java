package com.sta4l0rd.journalApp.service;

import com.sta4l0rd.journalApp.entity.JournalEntry;
import com.sta4l0rd.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry saveEntry(JournalEntry journalEntry){
        if(journalEntry != null) {
            journalEntry.setDateTime(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
            return journalEntry;
        }else {
            return null;
        }
    }

    public JournalEntry findById(ObjectId id){
        return journalEntryRepository.findById(id).orElse(null);
    }

    public int deleteById(ObjectId id){
        JournalEntry toBeDeleted = findById(id);
        if(toBeDeleted != null){
            journalEntryRepository.deleteById(id);
            return 0;
        }else {
            return -1;
        }
    }

    public JournalEntry replaceById(ObjectId id, JournalEntry journalEntry){
        if(journalEntry != null){
            if(journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty() && journalEntry.getContent() != null && !journalEntry.getContent().isEmpty()){
                JournalEntry old = findById(id);
                if(old != null){
                    old.setTitle(journalEntry.getTitle());
                    old.setContent(journalEntry.getContent());
                    journalEntryRepository.save(old);
                    return old;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    public JournalEntry modifyById(ObjectId id, JournalEntry journalEntry){
        if(journalEntry != null) {
            JournalEntry old = findById(id);
            if (old != null) {
                if (journalEntry.getContent() != null && !journalEntry.getContent().isEmpty()) {
                    old.setContent(journalEntry.getContent());
                    journalEntryRepository.save(old);
                }
                if (journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty()) {
                    old.setTitle(journalEntry.getTitle());
                    journalEntryRepository.save(old);
                }
                return old;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
}
