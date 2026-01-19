package com.abdus.localhirebackend.service;


import com.abdus.localhirebackend.dto.ResponseApplicationDto;
import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.JobApplication;
import com.abdus.localhirebackend.entity.JobPost;
import com.abdus.localhirebackend.entity.Type.ApplicationStatus;
import com.abdus.localhirebackend.repository.JobApplicationRepository;
import com.abdus.localhirebackend.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final JobPostRepository jobPostRepository;
    private final JobApplicationRepository jobApplicationRepository;
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    public List<JobPost> getAllJobs() {
        return jobPostRepository.findAll();
    }

    public ResponseEntity<?> apply(Long jobId) {

        JobPost job = jobPostRepository.findById(jobId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Job doest not exists")
        );

        JobApplication newJob = JobApplication.builder()
                .jobPost(job)
                .employee((AppUser) auth.getPrincipal())
                .status(ApplicationStatus.APPLIED)
                .build();
        jobApplicationRepository.save(newJob);
        return ResponseEntity.ok("saved");
    }

    public ResponseEntity<List<ResponseApplicationDto>> getMyJob() {
        AppUser user = (AppUser) auth.getPrincipal();
        List<ResponseApplicationDto> list = jobApplicationRepository.findAllByEmployee(user)
                .stream()
                .map(application -> ResponseApplicationDto.builder()
                        .id(application.getId())
                        .status(application.getStatus())
                        .job(application.getJobPost())
                        .build()
                ).toList();

        return ResponseEntity.ok(list);
    }
}
