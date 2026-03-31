package com.budgetapplication.budgetapp.dtos.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCategoryResponse {
    private  String  categoryName;
    private String categoryId;
    private String MonthlyBudgetId;
    private BigDecimal amount;
}
