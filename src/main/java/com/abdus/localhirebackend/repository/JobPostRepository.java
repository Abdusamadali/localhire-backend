package com.abdus.localhirebackend.repository;

import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost,Long>, JpaSpecificationExecutor<JobPost> {

    Optional<List<JobPost>> findByEmployer(AppUser user);

}
