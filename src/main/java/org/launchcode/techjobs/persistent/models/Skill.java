package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;

@Entity
public class Skill extends AbstractEntity {

    public String description;
    public Skill (String description){
        super();
        this.description = description;
    }

    public Skill () {} //no arg constructor for Hibernate

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
