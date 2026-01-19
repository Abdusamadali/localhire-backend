package com.abdus.localhirebackend.repository;

import com.abdus.localhirebackend.dto.RequestJobPostDto;
import com.abdus.localhirebackend.dto.ResponseApplicationDto;
import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.JobApplication;
import com.abdus.localhirebackend.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    List<JobApplication> findAllByEmployee(AppUser employee);
    List<JobApplication> findAllByJob(JobPost job);
}
