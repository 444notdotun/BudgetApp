package com.budgetapplication.budgetapp.exception;

import com.budgetapplication.budgetapp.dtos.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(BudgetCategoryDoesNotExist.class)
    public ResponseEntity<ApiResponse<String>> handleBudgetCategoryDoesNotExist(BudgetCategoryDoesNotExist budgetCategoryDoesNotExist){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(budgetCategoryDoesNotExist.getMessage(),null));
    }

    @ExceptionHandler(MonthlyBudgetException.class)
    public ResponseEntity<ApiResponse<String>> handleMonthlyBudgetException(MonthlyBudgetException monthlyBudgetException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(monthlyBudgetException.getMessage(),null));
    }

    @ExceptionHandler(MonthInputException.class)
    public ResponseEntity<ApiResponse<String>> handleMonthInputException(MonthInputException monthInputException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(monthInputException.getMessage(),null));
    }

    @ExceptionHandler(MonthlyBudgetDoesNotExistException.class)
    public ResponseEntity<ApiResponse<String >> handleMonthlyBudgetDoesnotExistException(MonthlyBudgetDoesNotExistException monthlyBudgetDoesNotExistException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(monthlyBudgetDoesNotExistException.getMessage(),null));
    }

    @ExceptionHandler(TemplateDoesNotExist.class)
    public ResponseEntity<ApiResponse<String>> handleTemplateDoesNotExist(TemplateDoesNotExist templateDoesNotExist){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(templateDoesNotExist.getMessage(),null));
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFound userNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(userNotFoundException.getMessage(),null));
    }
}
