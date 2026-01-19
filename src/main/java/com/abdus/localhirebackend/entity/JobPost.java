package com.abdus.localhirebackend.entity;

import com.abdus.localhirebackend.entity.Type.JobStatus;
import com.abdus.localhirebackend.entity.Type.JobType;
import com.abdus.localhirebackend.entity.Type.ShiftType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private String  title;

    private Integer salary;

    @Enumerated(EnumType.STRING)
    private ShiftType shiftType;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @ManyToOne
    private AppUser employer;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

}
