package com.budgetapplication.budgetapp.dtos.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class EditCategory {
    private String categoryName;
    private BigDecimal amount;
}
