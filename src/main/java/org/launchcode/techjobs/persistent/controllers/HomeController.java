package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers",employerRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam(required = false) Integer employerId) {

        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        }

        if (employerId == null){
            jobRepository.save(newJob);
        } else {
            Optional<Employer> employerResult = employerRepository.findById((employerId));
            if (employerResult.isEmpty()){
                jobRepository.save(newJob);
            } else {
                Employer employer = employerResult.get();
                newJob.setEmployer(employer);
            }
        }
        jobRepository.save(newJob);

        return "redirect:";
    }
    //emplpoyerId is name of variable
    //add code to select the chosen Employer... use the request parameter I added
    //only if valid... do I need to add a validation here? or a cascade?


    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
