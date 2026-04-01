package com.abdus.localhirebackend.repository;

import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.JobApplication;
import com.abdus.localhirebackend.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    List<JobApplication> findAllByEmployee(AppUser appUser);

    List<JobApplication> findAllByJobPost(JobPost jobPost);
}
