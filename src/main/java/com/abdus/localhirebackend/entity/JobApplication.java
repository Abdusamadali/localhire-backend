package com.abdus.localhirebackend.entity;

import com.abdus.localhirebackend.entity.Type.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@RequiredArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private JobPost jobPost;

    @ManyToOne
    private AppUser employee;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
}
