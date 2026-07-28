package com.rahul.shopease.Auth;

import com.rahul.shopease.DTO.Request.CustomerRequest;
import com.rahul.shopease.DTO.Request.LoginRequest;
import com.rahul.shopease.DTO.Response.CustomerResponse;
import com.rahul.shopease.DTO.Response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> register(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = authService.register(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse =authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
