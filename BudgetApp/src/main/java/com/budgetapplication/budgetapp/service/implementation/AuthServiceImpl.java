package com.budgetapplication.budgetapp.service.implementation;

import com.budgetapplication.budgetapp.data.models.Users;
import com.budgetapplication.budgetapp.data.repository.UserRepository;
import com.budgetapplication.budgetapp.dtos.request.LoginRequest;
import com.budgetapplication.budgetapp.dtos.request.RegisterRequest;
import com.budgetapplication.budgetapp.dtos.response.AuthResponse;
import com.budgetapplication.budgetapp.exception.UserNotFound;
import com.budgetapplication.budgetapp.service.interfac.AuthService;
import com.budgetapplication.budgetapp.service.interfac.JwtService;
import com.budgetapplication.budgetapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService {
    @Autowired
    UserRepository  userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        validateUser(request.getEmail());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Users users =Mapper.mapUserToRequest(request);
        userRepository.save(users);
        return Mapper.mapUserToResponse(users,jwtService.generateToken(users));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())) throw new UsernameNotFoundException("User not found");
       Users users =  userRepository.findByEmail(request.getEmail());
       validatePassword(request.getPassword(), users.getUserPassword());
       return  Mapper.mapUserToResponse(users,jwtService.generateToken(users));
    }

    private void  validateUser(String email){
        if(userRepository.existsByEmail(email)){
            throw new UserNotFound("Username exist");
        }
    }
    private void validatePassword(String password,String oldPassword){
        if(!passwordEncoder.matches(password,oldPassword)){
            throw new UsernameNotFoundException("incorrect password");
        }

    }
}
