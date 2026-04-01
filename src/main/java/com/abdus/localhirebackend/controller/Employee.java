package com.abdus.localhirebackend.controller;

import com.abdus.localhirebackend.dto.EmployerDto;
import com.abdus.localhirebackend.dto.ResponseApplicationDto;
import com.abdus.localhirebackend.dto.ResponseJobDto;
import com.abdus.localhirebackend.entity.JobPost;
import com.abdus.localhirebackend.entity.Type.JobType;
import com.abdus.localhirebackend.entity.Type.ShiftType;
import com.abdus.localhirebackend.repository.JobPostRepository;
import com.abdus.localhirebackend.service.EmployeeService;
import com.abdus.localhirebackend.utils.JobPostSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class Employee {
    private final EmployeeService employeeService;
    private final JobPostRepository jobPostRepository;


    @GetMapping
    ResponseEntity<List<ResponseJobDto>>getJobs(){

        List<ResponseJobDto> allJobs = employeeService.getAllJobs().stream().map(
           e-> {
               return ResponseJobDto.builder()
                         .jobType(e.getJobType())
                         .id(e.getId())
                         .employer(
                                 EmployerDto.builder()
                                         .username(e.getEmployer().getUsername())
                                         .id(e.getEmployer().getId())
                                         .build()
                         )
                         .salary(e.getSalary())
                         .shiftType(e.getShiftType())
                         .location(e.getLocation())
                         .status(e.getJobStatus())
                         .description(e.getDescription())
                         .build();
           }
        ).toList();
        return ResponseEntity.ok(allJobs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobPost>> filterJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer minSalary,
            @RequestParam(required = false) ShiftType shift,
            @RequestParam(required = false) JobType type,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state) {

        Specification<JobPost> spec = JobPostSpecifications.build(title, minSalary, shift, type, city, state);
        return ResponseEntity.ok(jobPostRepository.findAll(spec));
    }

    @PostMapping("/jobs/{id}")
    ResponseEntity<?>applyJob(@PathVariable Long id){
       return employeeService.apply(id);
    }

    @GetMapping("application")
    ResponseEntity<List<ResponseApplicationDto>>getMyApplication(){
        return employeeService.getMyJob();
    }
}
