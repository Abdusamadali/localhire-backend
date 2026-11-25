package com.abdus.localhirebackend.service;


import com.abdus.localhirebackend.dto.EmployerDto;
import com.abdus.localhirebackend.dto.ResponseAppUserDto;
import com.abdus.localhirebackend.dto.ResponseApplicationDto;
import com.abdus.localhirebackend.dto.ResponseJobDto;
import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.JobApplication;
import com.abdus.localhirebackend.entity.JobPost;
import com.abdus.localhirebackend.entity.Type.ApplicationStatus;
import com.abdus.localhirebackend.repository.JobApplicationRepository;
import com.abdus.localhirebackend.repository.JobPostRepository;
import com.abdus.localhirebackend.utils.JobMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final JobPostRepository jobPostRepository;
    private final JobApplicationRepository jobApplicationRepository;




    public List<JobPost> getAllJobs() {
        return jobPostRepository.findAll();
    }

    public ResponseEntity<?> apply(Long jobId) {
        JobPost job = jobPostRepository.findById(jobId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Job doest not exists")
        );


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser  user =(AppUser) auth.getPrincipal();

        List<JobApplication> allByEmployee = jobApplicationRepository.findAllByEmployee(user);
        allByEmployee.forEach((x)->{
            if(Objects.equals(x.getJobPost().getId(), jobId)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"application already exist");
            }
        });

        JobApplication newJob = JobApplication.builder()
                .jobPost(job)
                .employee(user)
                .employer(job.getEmployer())
                .status(ApplicationStatus.APPLIED)
                .build();
        jobApplicationRepository.save(newJob);
        return ResponseEntity.ok("saved");
    }

    public ResponseEntity<List<ResponseApplicationDto>> getMyJob() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) auth.getPrincipal();
        List<ResponseApplicationDto> list = jobApplicationRepository.findAllByEmployee(user)
                .stream()
                .map(application -> ResponseApplicationDto.builder()
                        .id(application.getId())
                        .employee(
                                ResponseAppUserDto.builder()
                                        .id(application.getEmployee().getId())
                                        .username(application.getEmployee().getUsername())
                                        .build()
                        )
                        .employer(
                                ResponseAppUserDto.builder()
                                        .id(application.getEmployer().getId())
                                        .username(application.getEmployer().getUsername())
                                        .build()
                        )
                        .status(application.getStatus())
                        .jobPost(
                                ResponseJobDto.builder()
                                        .jobType(application.getJobPost().getJobType())
                                        .minSalary(application.getJobPost().getMinSalary())
                                        .maxSalary(application.getJobPost().getMaxSalary())
                                        .id(application.getJobPost().getId())
                                        .shiftType(application.getJobPost().getShiftType())
                                        .employer(
                                                EmployerDto.builder()
                                                        .id(application.getEmployer().getId())
                                                        .username(application.getEmployer().getUsername())
                                                        .build()
                                        )
                                        .location(application.getJobPost().getLocation())
                                        .description(application.getJobPost().getDescription())
                                        .status(application.getJobPost().getJobStatus())
                                        .build()
                        ).build()
                ).toList();

        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<ResponseJobDto>>search( Specification<JobPost> spec){
        List<ResponseJobDto>jobs = jobPostRepository.findAll(spec).stream()
                .map(JobMapper::toDto).toList();
        return ResponseEntity.ok(jobs);
    }
}
