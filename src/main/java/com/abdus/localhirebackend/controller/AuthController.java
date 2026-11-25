package com.abdus.localhirebackend.controller;


import com.abdus.localhirebackend.dto.LoginReqDto;
import com.abdus.localhirebackend.dto.SignupDto;
import com.abdus.localhirebackend.service.AppUserService;
import com.abdus.localhirebackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserService appUserService;
    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<?>createUser(@RequestBody SignupDto user){
                return appUserService.signup(user);

    }


    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto){
        return ResponseEntity.ok(authService.login(loginReqDto));
    }

}
