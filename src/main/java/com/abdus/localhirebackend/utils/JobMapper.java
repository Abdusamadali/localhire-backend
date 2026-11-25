package com.abdus.localhirebackend.utils;

import com.abdus.localhirebackend.dto.EmployerDto;
import com.abdus.localhirebackend.dto.ResponseJobDto;
import com.abdus.localhirebackend.entity.JobPost;

public class JobMapper {
    public static ResponseJobDto toDto(JobPost e){
        return ResponseJobDto.builder()
                .jobType(e.getJobType())
                .id(e.getId())
                .employer(
                        EmployerDto.builder()
                                .username(e.getEmployer().getUsername())
                                .id(e.getEmployer().getId())
                                .build()
                )
                .minSalary(e.getMinSalary())
                .maxSalary(e.getMaxSalary())
                .shiftType(e.getShiftType())
                .location(e.getLocation())
                .status(e.getJobStatus())
                .description(e.getDescription())
                .build();
    }
}
