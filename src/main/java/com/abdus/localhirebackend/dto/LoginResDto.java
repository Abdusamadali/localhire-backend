package com.abdus.localhirebackend.dto;

import jakarta.persistence.Id;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginResDto {
    private String token;
    private Long id;
    private String role;
}
