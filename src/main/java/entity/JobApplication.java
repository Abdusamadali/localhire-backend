package entity;

import entity.Type.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class JobApplication {

    @Id
    private Long id;

    @ManyToOne
    private JobPost jobPost;

    @ManyToOne
    private AppUser employee;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
}
