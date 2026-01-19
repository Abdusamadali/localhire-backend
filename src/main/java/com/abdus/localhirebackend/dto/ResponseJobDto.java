package com.abdus.localhirebackend.dto;

import com.abdus.localhirebackend.controller.Employer;
import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.Location;
import com.abdus.localhirebackend.entity.Type.JobStatus;
import com.abdus.localhirebackend.entity.Type.JobType;
import com.abdus.localhirebackend.entity.Type.ShiftType;

public class ResponseJobDto {
    private Long id;
    private String title;
    private Integer salary;
    private JobStatus status;
    private ShiftType shiftType;
    private JobType jobType;
    private Location location;
    private AppUser employer;
}
