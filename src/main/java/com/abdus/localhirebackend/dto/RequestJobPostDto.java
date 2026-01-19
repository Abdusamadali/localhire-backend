package com.abdus.localhirebackend.dto;

import com.abdus.localhirebackend.entity.Location;
import com.abdus.localhirebackend.entity.Type.JobStatus;
import com.abdus.localhirebackend.entity.Type.JobType;
import com.abdus.localhirebackend.entity.Type.ShiftType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestJobPostDto {

    private String title;
    private Integer salary;
    private JobStatus status;
    private ShiftType shiftType;
    private JobType jobType;
    private Location location;
}
