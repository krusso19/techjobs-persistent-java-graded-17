package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank
    @Size(max = 50)
    public String location;

    //Add Public Accessor Methods for location
    public Employer (String location) {
        super();
        this.location = location;

    }
    public Employer () {} //no arg constructor required for hibernate

    @NotBlank @Size(max = 50)
    public String getLocation() {
        return location;
    }

    @NotBlank @Size(max = 50)
    public void setLocation(String location) {
        this.location = location;
    }
}
