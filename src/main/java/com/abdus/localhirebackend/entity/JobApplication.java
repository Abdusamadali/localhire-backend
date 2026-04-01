package com.abdus.localhirebackend.entity;

import com.abdus.localhirebackend.entity.Type.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor   //  for JPA
@AllArgsConstructor  //  for @Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private JobPost jobPost;

    @ManyToOne
    private AppUser employer;

    @ManyToOne
    private AppUser employee;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
}
