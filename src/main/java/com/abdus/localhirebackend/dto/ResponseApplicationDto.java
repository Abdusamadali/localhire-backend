package com.abdus.localhirebackend.dto;

import com.abdus.localhirebackend.entity.JobPost;
import com.abdus.localhirebackend.entity.Type.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Builder
public class ResponseApplicationDto {

    private Long id;
    private JobPost job;
    private ApplicationStatus status;
}
