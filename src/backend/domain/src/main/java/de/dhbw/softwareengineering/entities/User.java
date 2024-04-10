package de.dhbw.softwareengineering.entities;

import de.dhbw.softwareengineering.exceptions.InvalidUserException;
import de.dhbw.softwareengineering.valueobjects.UserName;
import jakarta.persistence.Id;

import java.util.UUID;

public class User {
    @Id
    private UUID id;
    private UserName name;

    public User(UUID id, UserName name) {
        if(id == null || name == null) throw new InvalidUserException("Wrong user parameters!");
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserName getName() {
        return name;
    }

    public void setName(UserName name) {
        this.name = name;
    }
}
