package com.budgetapplication.budgetapp.controller;

import com.budgetapplication.budgetapp.dtos.request.LoginRequest;
import com.budgetapplication.budgetapp.dtos.request.RegisterRequest;
import com.budgetapplication.budgetapp.dtos.response.ApiResponse;
import com.budgetapplication.budgetapp.dtos.response.AuthResponse;
import com.budgetapplication.budgetapp.service.interfac.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    AuthService  authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("registration is successful",authService.register(registerRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("login is successful",authService.login(loginRequest)));
    }


}
