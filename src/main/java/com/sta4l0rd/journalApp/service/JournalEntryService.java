package com.sta4l0rd.journalApp.service;

import com.sta4l0rd.journalApp.entity.JournalEntry;
import com.sta4l0rd.journalApp.entity.JournalUser;
import com.sta4l0rd.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    private JournalEntry findById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public List<JournalEntry> readAllEntries(String username) {
        JournalUser journalUser = userService.findUser(username);
        if (journalUser != null) {
            return journalUser.getJournalEntries();
        } else {
            return null;
        }
    }

    public JournalEntry createEntry(JournalEntry journalEntry, String username) {
        JournalUser journalUser = userService.findUser(username);
        if (journalUser != null && journalEntry != null && journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty()) {
            journalEntry.setDateTime(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            journalUser.getJournalEntries().add(saved);
            userService.updateUser(journalUser);
            return journalEntry;
        } else {
            return null;
        }
    }

    public JournalEntry findById(ObjectId id, String username) {
        JournalUser journalUser = userService.findUser(username);
        JournalEntry journalEntry = findById(id);
        if (journalUser != null && journalEntry != null && journalUser.getJournalEntries().contains(journalEntry)) {
            return journalEntry;
        } else {
            return null;
        }
    }

    public int deleteById(ObjectId id, String username) {
        JournalEntry toBeDeleted = findById(id, username);
        if (toBeDeleted != null) {
            JournalUser journalUser = userService.findUser(username);
            journalUser.getJournalEntries().remove(toBeDeleted);
            journalEntryRepository.deleteById(id);
            return 0;
        } else {
            return -1;
        }
    }

    public JournalEntry replaceById(ObjectId id, JournalEntry journalEntry, String username) {
        JournalEntry old = findById(id, username);
        if (journalEntry != null && journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty() && old != null) {
            old.setTitle(journalEntry.getTitle());
            old.setContent(journalEntry.getContent());
            journalEntryRepository.save(old);
            return old;
        } else {
            return null;
        }
    }

    public JournalEntry modifyById(ObjectId id, JournalEntry journalEntry, String username) {
        JournalEntry old = findById(id, username);
        if (journalEntry != null && old != null) {
            if (journalEntry.getContent() != null && !journalEntry.getContent().isEmpty()) {
                old.setContent(journalEntry.getContent());
                journalEntryRepository.save(old);
            }
            if (journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty()) {
                old.setTitle(journalEntry.getTitle());
                journalEntryRepository.save(old);
            }
            return old;
        } else {
            return null;
        }
    }
}
