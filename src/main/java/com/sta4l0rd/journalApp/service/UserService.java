package com.sta4l0rd.journalApp.service;

import com.sta4l0rd.journalApp.entity.JournalUser;
import com.sta4l0rd.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public JournalUser findUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<JournalUser> getAll(){
        return userRepository.findAll();
    }

    public JournalUser updateUser(JournalUser journalUser){
        userRepository.save(journalUser);
        return journalUser;
    }

    public JournalUser saveUser(JournalUser journalUser){
        journalUser.setPassword(passwordEncoder.encode(journalUser.getPassword()));
        userRepository.save(journalUser);
        return journalUser;
    }

}
