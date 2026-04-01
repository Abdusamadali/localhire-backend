package com.abdus.localhirebackend.service;

import com.abdus.localhirebackend.dto.SignupDto;
import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.entity.Type.Role;
import com.abdus.localhirebackend.exceptions.InvalidRoleException;
import com.abdus.localhirebackend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

   public void signup(SignupDto signupDto){

       if(appUserRepository.existsByUsernameIgnoreCase(signupDto.getUsername())){
           throw new IllegalArgumentException("user already exists");
       }

        if(Objects.equals(signupDto.getRole(), "Admin")){
            throw new InvalidRoleException("Admin user cannot be created");
        }
        AppUser user = AppUser.builder()
                .username(signupDto.getUsername())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .role(Role.valueOf(signupDto.getRole()))
                .build();
        appUserRepository.save(user);
    }

}
