package com.sta4l0rd.journalApp.repository;

import com.sta4l0rd.journalApp.entity.JournalUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<JournalUser, ObjectId> {
    JournalUser findByUsername(String username);
}
