package com.budgetapplication.budgetapp.service.interfac;


import com.budgetapplication.budgetapp.dtos.request.LoginRequest;
import com.budgetapplication.budgetapp.dtos.request.RegisterRequest;
import com.budgetapplication.budgetapp.dtos.response.AuthResponse;

public interface AuthService {
        AuthResponse register(RegisterRequest request);
        AuthResponse login(LoginRequest request);
    }

