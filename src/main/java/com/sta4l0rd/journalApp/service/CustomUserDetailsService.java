package com.sta4l0rd.journalApp.service;

import com.sta4l0rd.journalApp.entity.JournalUser;
import com.sta4l0rd.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JournalUser user = userRepository.findByUsername(username);
        if(user != null){
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username" + username);
    }
}
