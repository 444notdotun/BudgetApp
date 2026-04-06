package com.budgetapplication.budgetapp.service.interfac;

import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.request.EditBudgetRequest;
import com.budgetapplication.budgetapp.dtos.response.*;

public interface MonthlyBudgetService {
    CreateMonthlyBudgetResponse createMonthlyBudget(String userId, CreateMonthlyBudgetRequest createMonthlyBudgetRequest);

    CreateMonthlyBudgetResponse editMontlyBudget(EditBudgetRequest createMonthlyBudgetRequest, String monthlyBudgetId);

    ViewAllMonthlyBudget viewAll(String userId);

    DeleteResponse deleteMonthlyBudget(String userId, String monthlyBudgetId);

    CreateCategoryResponse addCategory(CreateCategoryRequest createCategoryRequest, String monthlyBudgetId);

    TotalExpenseResponse calculateTotalExpense(String monthlyBudgetId);

    TotalSavingsResponse totalSavings(String monthlyBudgetId);

    TotalRemainingBalance getBalance(String monthlyBudgetId);
}

