package com.budgetapplication.budgetapp.dtos.response;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ViewCategory {
    private List<BudgetCategories> budgetCategoriesList;

    public  ViewCategory() {
        budgetCategoriesList = new ArrayList<>();
    }
}
