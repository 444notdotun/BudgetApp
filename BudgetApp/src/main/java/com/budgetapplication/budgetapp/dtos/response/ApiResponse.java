package com.budgetapplication.budgetapp.dtos.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(String message, T data){
        this.success = data != null;
        this.message = message;
        this.data = data;
    }

//    public ApiResponse(AuthResponse register) {
//        this.success = data!=null;
//        this.message = getMessage();
//        this.data = register;
//
//    }
}


