package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank
    @Size(max = 50)
    public String location;

    @OneToMany//(mappedBy = "employer") //how does hibernate know which jobs are in a given employer? Name of field
    @JoinColumn(name = "employer_id") //says you can't have both?
    private final List<Job> jobs = new ArrayList<>();

    public Employer (String location) { //might not be necessary
        super();
        this.location = location;

    }
    public Employer () {} //no arg constructor required for hibernate

    public String getLocation() {
        return location;
    }

        public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
