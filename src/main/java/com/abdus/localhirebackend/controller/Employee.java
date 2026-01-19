package com.abdus.localhirebackend.controller;

import com.abdus.localhirebackend.dto.ResponseApplicationDto;
import com.abdus.localhirebackend.entity.JobPost;
import com.abdus.localhirebackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class Employee {
    private final EmployeeService employeeService;

    @GetMapping
    ResponseEntity<List<JobPost>>getJobs(){
        List<JobPost> allJobs = employeeService.getAllJobs();
        return ResponseEntity.ok(allJobs);
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
