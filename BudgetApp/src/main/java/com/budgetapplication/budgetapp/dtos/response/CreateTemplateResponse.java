package com.budgetapplication.budgetapp.dtos.response;

import lombok.Data;

import java.util.List;
@Data
public class CreateTemplateResponse {
    private String templateId;
    private int year;
    private List<CreateMonthlyBudgetResponse> monthlyBudgets;

}
