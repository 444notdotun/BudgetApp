package com.budgetapplication.budgetapp.dtos.response;

import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ViewAllMonthlyBudget {
    private List<MonthlyBudget> monthlyBudgetList;

    public  ViewAllMonthlyBudget() {
        this.monthlyBudgetList=new ArrayList<>();
    }
}
