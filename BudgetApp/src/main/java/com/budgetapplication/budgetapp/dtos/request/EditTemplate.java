package com.budgetapplication.budgetapp.dtos.request;

import com.budgetapplication.budgetapp.data.models.Users;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class EditTemplate {
    @Min(value = 2025)
    private int Year;


}
