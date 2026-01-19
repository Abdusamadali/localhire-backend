package com.abdus.localhirebackend.controller;


import com.abdus.localhirebackend.dto.RequestJobPostDto;
import com.abdus.localhirebackend.dto.ResponseApplicationDto;
import com.abdus.localhirebackend.dto.ResponseJobDto;
import com.abdus.localhirebackend.entity.JobApplication;
import com.abdus.localhirebackend.entity.JobPost;
import com.abdus.localhirebackend.repository.JobPostRepository;
import com.abdus.localhirebackend.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employer")
@RequiredArgsConstructor
public class Employer {

    private final EmployerService employerService;

    @PostMapping
    ResponseEntity<?>createJob(@RequestBody RequestJobPostDto dto){
        return employerService.createJob(dto);
    }

    @GetMapping
    ResponseEntity<List<JobPost>>getJobs(){
        return employerService.getJobs();
    }

    @GetMapping("/jobs/{jobId}/applications")
    ResponseEntity<List<JobApplication>>getJobsApplications(@PathVariable Long jobId){
        return ResponseEntity.ok(employerService.getApplicationByJobId(jobId));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateJob(@RequestBody RequestJobPostDto dto, @PathVariable Long id){
        return employerService.updateJob(dto,id);
    }

    @PutMapping("/{id}/close")
    ResponseEntity<?> closeJob(@PathVariable Long id){
        return employerService.closeJob(id);
    }

    @PutMapping("{id}/accept")
    ResponseEntity<?>acceptJobApplication(@PathVariable Long id){
        return employerService.acceptJobApplication(id);
    }

    @PutMapping("{id}/reject")
    ResponseEntity<?>rejectJobApplication(@PathVariable Long id){
        return employerService.rejectJobApplication(id);
    }


}
