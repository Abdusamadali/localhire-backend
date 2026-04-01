package com.abdus.localhirebackend.service;

import com.abdus.localhirebackend.dto.LoginReqDto;
import com.abdus.localhirebackend.dto.LoginResDto;
import com.abdus.localhirebackend.entity.AppUser;
import com.abdus.localhirebackend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginResDto login(LoginReqDto loginReqDto){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReqDto.getUsername(), loginReqDto.getPassword()));
            AppUser user = (AppUser) authenticate.getPrincipal();
            String token = jwtUtil.generateToken(user);
            System.out.println(token);
            return new LoginResDto(token, user.getId(),user.getRole().toString());
        }catch (AuthenticationException e){
            throw  new BadCredentialsException("Invalid username or password");
        }
    }
}
