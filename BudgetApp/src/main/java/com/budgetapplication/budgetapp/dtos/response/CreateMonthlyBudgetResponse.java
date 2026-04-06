package com.budgetapplication.budgetapp.dtos.response;

import com.budgetapplication.budgetapp.data.models.BudgetTemplate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

@Data
public class CreateMonthlyBudgetResponse {
    private String monthlyBudgetId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Month month;
    private int year;
    private BigDecimal income;
    private BudgetTemplate templateId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
