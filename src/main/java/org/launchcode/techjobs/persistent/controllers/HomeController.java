package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
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
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers",employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        }

            Optional<Employer> employerResult = employerRepository.findById((employerId));
            if (employerResult.isEmpty()){
                jobRepository.save(newJob);
            } else {
                Employer employer = employerResult.get();
                newJob.setEmployer(employer);
            }


            List<Skill> skillResult = (List<Skill>) skillRepository.findAllById(skills); //given
            newJob.setSkills(skillResult); //given


        jobRepository.save(newJob);

        return "redirect:";
    }
    //emplpoyerId is name of variable
    //add code to select the chosen Employer... use the request parameter I added
    //only if valid... do I need to add a validation here? or a cascade?


    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> jobResult = jobRepository.findById(jobId);
        if (jobResult.isEmpty()){
            return "redirect: ";
        } else {
            Job job = jobResult.get();
            model.addAttribute("job", job);
        }
            return "view";
    }

}
