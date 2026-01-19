package com.abdus.localhirebackend.repository;

import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost,Long> {

    Optional<List<JobPost>> findByEmployer(AppUser user);

}
