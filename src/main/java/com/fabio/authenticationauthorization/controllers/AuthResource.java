package com.fabio.authenticationauthorization.controllers;

import com.fabio.authenticationauthorization.dtos.EmailDto;
import com.fabio.authenticationauthorization.repositories.CustomerRepository;
import com.fabio.authenticationauthorization.security.JWTUtil;
import com.fabio.authenticationauthorization.security.UserSS;
import com.fabio.authenticationauthorization.services.AuthService;
import com.fabio.authenticationauthorization.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto emailDto){
        authService.sendNewPassword(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }


}
