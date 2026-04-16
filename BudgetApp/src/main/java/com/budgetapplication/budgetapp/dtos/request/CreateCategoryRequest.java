package com.budgetapplication.budgetapp.dtos.request;

import com.budgetapplication.budgetapp.data.models.CategoryType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCategoryRequest {
    @NotBlank
    private String categoryName;
    @NotNull
    @Min(value = 0, message = "amount should not be less than zero")
    private BigDecimal amount;
    @NotBlank
    private CategoryType  categoryType;
}
