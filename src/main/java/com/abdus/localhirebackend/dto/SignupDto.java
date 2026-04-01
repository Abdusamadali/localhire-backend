package com.abdus.localhirebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupDto {
  private   String username;
  private   String password;
  private String role;
}
