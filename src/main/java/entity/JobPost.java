package entity;

import entity.Type.JobStatus;
import entity.Type.JobType;
import entity.Type.ShiftType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
