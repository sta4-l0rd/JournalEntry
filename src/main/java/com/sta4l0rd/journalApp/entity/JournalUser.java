package com.sta4l0rd.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@lombok.Data
public class JournalUser {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @lombok.NonNull
    private String username;

    @lombok.NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
