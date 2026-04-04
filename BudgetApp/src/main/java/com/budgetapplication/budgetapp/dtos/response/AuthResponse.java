package com.budgetapplication.budgetapp.dtos.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String email;
    private String userId;
}
