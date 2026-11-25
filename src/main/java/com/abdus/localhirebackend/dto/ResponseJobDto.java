package com.abdus.localhirebackend.dto;

import com.abdus.localhirebackend.controller.Employer;
import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.Location;
import com.abdus.localhirebackend.entity.Type.ApplicationStatus;
import com.abdus.localhirebackend.entity.Type.JobStatus;
import com.abdus.localhirebackend.entity.Type.JobType;
import com.abdus.localhirebackend.entity.Type.ShiftType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class ResponseJobDto {
    private Long id;
    private Integer minSalary;
    private  Integer maxSalary;
    private JobStatus status;
    private ShiftType shiftType;
    private JobType jobType;
    private Location location;
    private EmployerDto employer;
    private String description;
}
