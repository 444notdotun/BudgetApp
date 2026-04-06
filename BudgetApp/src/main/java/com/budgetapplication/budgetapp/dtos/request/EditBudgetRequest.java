package com.budgetapplication.budgetapp.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class EditBudgetRequest {
    private int year;
    private String month;
    private BigDecimal income;
}
