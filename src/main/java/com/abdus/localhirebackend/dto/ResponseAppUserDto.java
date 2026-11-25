package com.abdus.localhirebackend.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseAppUserDto {
    Long id;
    String username;
}
