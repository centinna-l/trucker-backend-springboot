package com.jerry.trucker.truckerapplication.controller;


import com.jerry.trucker.truckerapplication.payload.JwtAuthResponse;
import com.jerry.trucker.truckerapplication.payload.LoginDto;
import com.jerry.trucker.truckerapplication.payload.RegisterDto;
import com.jerry.trucker.truckerapplication.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/testing")
    public ResponseEntity<String> testRoute() {
        return new ResponseEntity<>("API is running well", HttpStatus.OK);
    }

}
