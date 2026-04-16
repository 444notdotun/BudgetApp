package com.budgetapplication.budgetapp.controller;

import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.request.EditBudgetRequest;
import com.budgetapplication.budgetapp.dtos.response.*;
import com.budgetapplication.budgetapp.service.interfac.MonthlyBudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monthlyBudget")
public class MonthlyBudgetController {
    @Autowired
    private MonthlyBudgetService monthlyBudgetService;

    @PostMapping("/createMonthlyBudget")
    public ResponseEntity<ApiResponse<CreateMonthlyBudgetResponse>> createMonthlyBudget(@AuthenticationPrincipal String userId, @Valid @RequestBody CreateMonthlyBudgetRequest createMonthlyBudgetRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        "MonthlyBudget has been created",monthlyBudgetService
                        .createMonthlyBudget(userId,createMonthlyBudgetRequest)));
    }

    @PatchMapping("/editMonthlyBudget/{monthlyBudgetId}")
    public ResponseEntity<ApiResponse<CreateMonthlyBudgetResponse>> editMonthlyBudget(@Valid @RequestBody EditBudgetRequest createMonthlyBudgetRequest, @PathVariable String monthlyBudgetId){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ApiResponse<>("MonthlyBudgetId has been edited",monthlyBudgetService
                        .editMontlyBudget(createMonthlyBudgetRequest,monthlyBudgetId)));
    }

    @GetMapping("/viewAll")
    public  ResponseEntity<ApiResponse<ViewAllMonthlyBudget>> viewAllBudgets(@AuthenticationPrincipal String userId){
        return ResponseEntity.status(HttpStatus.OK).
                body(new ApiResponse<>("budgets found!",monthlyBudgetService
                        .viewAll(userId)));
    }

    @DeleteMapping("/delete/{monthlyBudgetId}")
    public ResponseEntity<ApiResponse<DeleteResponse>> deleteMonthlyBudget(@AuthenticationPrincipal String userId ,@PathVariable String monthlyBudgetId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("monthlyBudget deleted Suceessfully!",monthlyBudgetService
                        .deleteMonthlyBudget(userId,monthlyBudgetId)));

    }

    @PostMapping("/addCategory/{monthlyBudgetId}")
    public ResponseEntity<ApiResponse<CreateCategoryResponse>> addCategory(
            @AuthenticationPrincipal String userId,
            @PathVariable String monthlyBudgetId,
            @Valid @RequestBody CreateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Category added",
                        monthlyBudgetService.addCategory(request, monthlyBudgetId)));
    }

    @GetMapping("/{monthlyBudgetId}/expenses")
    public ResponseEntity<ApiResponse<TotalExpenseResponse>> getTotalExpense(
            @PathVariable String monthlyBudgetId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Total expenses",
                        monthlyBudgetService.calculateTotalExpense(monthlyBudgetId)));
    }

    @GetMapping("/{monthlyBudgetId}/savings")
    public ResponseEntity<ApiResponse<TotalSavingsResponse>> getTotalSavings(
            @PathVariable String monthlyBudgetId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Total savings",
                        monthlyBudgetService.totalSavings(monthlyBudgetId)));
    }

    @GetMapping("/{monthlyBudgetId}/balance")
    public ResponseEntity<ApiResponse<TotalRemainingBalance>> getBalance(
            @PathVariable String monthlyBudgetId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Remaining balance",
                        monthlyBudgetService.getBalance(monthlyBudgetId)));
    }


}
