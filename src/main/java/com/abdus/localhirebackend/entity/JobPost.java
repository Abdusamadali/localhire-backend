package com.abdus.localhirebackend.entity;

import com.abdus.localhirebackend.entity.Type.JobStatus;
import com.abdus.localhirebackend.entity.Type.JobType;
import com.abdus.localhirebackend.entity.Type.ShiftType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor   //  for JPA
@AllArgsConstructor  //  for Builder
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private Integer minSalary;
    private Integer maxSalary;

    @Enumerated(EnumType.STRING)
    private ShiftType shiftType;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @ManyToOne
    private AppUser employer;

    private  String description;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
}
