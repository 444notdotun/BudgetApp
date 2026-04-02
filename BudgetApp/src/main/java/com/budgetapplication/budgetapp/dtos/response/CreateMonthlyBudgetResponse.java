package com.budgetapplication.budgetapp.dtos.response;

import com.budgetapplication.budgetapp.data.models.BudgetTemplate;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

@Data
public class CreateMonthlyBudgetResponse {
    private String monthlyBudgetId;
    private Month month;
    private int year;
    private BigDecimal income;
    private BudgetTemplate templateId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
