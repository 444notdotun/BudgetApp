package com.budgetapplication.budgetapp.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreateMonthlyBudgetRequest {
    @Min(value = 2025)
    private int Year;
    @NotBlank
    private String Month;
    @Min(value = 0)
    @NotBlank
    private BigDecimal income;

}
