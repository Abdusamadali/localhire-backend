package com.abdus.localhirebackend.utils;

import com.abdus.localhirebackend.entity.JobPost;
import com.abdus.localhirebackend.entity.Type.JobType;
import com.abdus.localhirebackend.entity.Type.ShiftType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobPostSpecifications {

    public static Specification<JobPost> build(
            String title, Integer minSalary, ShiftType shift,
            JobType type, String city, String state) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            if (minSalary != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("salary"), minSalary));
            }
            if (shift != null) {
                predicates.add(cb.equal(root.get("shiftType"), shift));
            }
            if (type != null) {
                predicates.add(cb.equal(root.get("jobType"), type));
            }
            // Embedded Location filtering
            if (city != null && !city.isBlank()) {
                predicates.add(cb.equal(root.get("location").get("city"), city));
            }
            if (state != null && !state.isBlank()) {
                predicates.add(cb.equal(root.get("location").get("state"), state));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
