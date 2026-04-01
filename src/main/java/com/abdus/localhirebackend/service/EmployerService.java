package com.abdus.localhirebackend.service;

import com.abdus.localhirebackend.dto.EmployerDto;
import com.abdus.localhirebackend.dto.RequestJobPostDto;
import com.abdus.localhirebackend.dto.ResponseApplicationDto;
import com.abdus.localhirebackend.dto.ResponseJobDto;
import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.JobApplication;
import com.abdus.localhirebackend.entity.JobPost;
import com.abdus.localhirebackend.entity.Type.ApplicationStatus;
import com.abdus.localhirebackend.entity.Type.JobStatus;
import com.abdus.localhirebackend.repository.JobApplicationRepository;
import com.abdus.localhirebackend.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployerService {
    final JobPostRepository jobPostRepository;
    final JobApplicationRepository jobApplicationRepository;

//    @PreAuthorize("hasRole('EMPLOYER')")
    public  ResponseEntity<?> updateJob(RequestJobPostDto dto, Long id) {
        AppUser user = (AppUser) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();

        JobPost job = jobPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"job does not exist"));


        if (user!= null && !job.getEmployer().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed to update this job");
        }

            job.setJobStatus(dto.getStatus());
            job.setJobType(dto.getJobType());
            job.setSalary(dto.getSalary());
            job.setShiftType(dto.getShiftType());
            job.setLocation(dto.getLocation());

           return ResponseEntity.ok( jobPostRepository.save(job));

    }


    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<?> createJob(RequestJobPostDto dto) {
        AppUser user =(AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        JobPost jobPost=JobPost.builder()
                .jobType(dto.getJobType())
                .employer(user)
                .jobStatus(JobStatus.OPEN)
                .location(dto.getLocation())
                .shiftType(dto.getShiftType())
                .salary(dto.getSalary())
                .build();
        JobPost save = jobPostRepository.save(jobPost);
        ResponseJobDto jobDto = ResponseJobDto.builder()
                .employer(
                     EmployerDto.builder()
                             .id(save.getEmployer().getId())
                             .username(save.getEmployer().getUsername())
                             .build()
                )
                .jobType(save.getJobType())
                .id(save.getId())
                .location(save.getLocation())
                .status(save.getJobStatus())
                .salary(save.getSalary())
                .shiftType(save.getShiftType())
                .build();
        return new ResponseEntity<>(jobDto, HttpStatus.OK);
    }

    public ResponseEntity<List<ResponseJobDto>> getJobs() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) auth.getPrincipal();
        List<JobPost> jobs = jobPostRepository.findByEmployer(user).orElse(null);
        List<ResponseJobDto>list = jobs.stream().map((e)->{
          return ResponseJobDto.builder()
                   .id(e.getId())
                   .location(e.getLocation())
                   .status(e.getJobStatus())
                   .salary(e.getSalary())
                   .employer(
                           EmployerDto.builder()
                                   .username(e.getEmployer().getUsername())
                                   .id(e.getEmployer().getId())
                                   .build()
                   )
                   .jobType(e.getJobType())
                   .shiftType(e.getShiftType())
                   .build();
        }).toList();
        return ResponseEntity.ok(list);
    }

    public List<JobApplication> getApplicationByJobId(Long jobId) {
            AppUser user =(AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            JobPost job = jobPostRepository.findById(jobId).orElseThrow(
                    ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job does not exits ")
            );

            if(!job.getEmployer().getId().equals(user.getId()))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not allowed to view applications for this job");

            return jobApplicationRepository.findAllByJobPost(job);

    }

    public ResponseEntity<?> closeJob(Long id){
        JobPost jobPost = jobPostRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job doest not exist")
        );
        jobPost.setJobStatus(JobStatus.CLOSED);
        jobPostRepository.save(jobPost);
        return ResponseEntity.ok("Job closed");
    }

    public ResponseEntity<?> acceptJobApplication(Long id) {
        JobApplication application = jobApplicationRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Job application does not exists"));
        application.setStatus(ApplicationStatus.ACCEPTED);
        jobApplicationRepository.save(application);
        return ResponseEntity.ok("accepted");
    }

    public ResponseEntity<?> rejectJobApplication(Long id) {
        JobApplication application = jobApplicationRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Job does not exists"));
        application.setStatus(ApplicationStatus.REJECTED);
        jobApplicationRepository.save(application);
        return ResponseEntity.ok("rejected application");
    }
}
