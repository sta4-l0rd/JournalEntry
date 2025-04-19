package com.sta4l0rd.journalApp.service;

import com.sta4l0rd.journalApp.entity.JournalEntry;
import com.sta4l0rd.journalApp.entity.User;
import com.sta4l0rd.journalApp.repository.JournalEntryRepository;
import com.sta4l0rd.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    private User findUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    public List<JournalEntry> getAllJournalEntries(String username) {
        User user = findUser(username);
        if(user != null){
            return user.getJournalEntries();
        }
        else{
            return null;
        }
    }
}
