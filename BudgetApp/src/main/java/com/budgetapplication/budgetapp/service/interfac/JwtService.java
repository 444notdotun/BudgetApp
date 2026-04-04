package com.budgetapplication.budgetapp.service.interfac;

import com.budgetapplication.budgetapp.data.models.Users;

public interface JwtService {
    String generateToken(Users user);
    boolean validateToken(String token);
    String extractUserId(String token);
}
