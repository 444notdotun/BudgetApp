package com.budgetapplication.budgetapp.utils;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.response.CreateCategoryResponse;
import tools.jackson.databind.ObjectMapper;

public class Mapper {
    public static BudgetCategories maprequestToCategory(CreateCategoryRequest createCategories, MonthlyBudget budgetId) {
        BudgetCategories budgetCategories = new BudgetCategories();
        budgetCategories.setCategoryName(createCategories.getCategoryName());
        budgetCategories.setAmount(createCategories.getAmount());
        budgetCategories.setMonthlyBudgetId(budgetId);
        return  budgetCategories;
    }

    public static CreateCategoryResponse mapBudgetCategoryToResponse(BudgetCategories budgetCategories) {
        CreateCategoryResponse createCategoryResponse = new CreateCategoryResponse();
        createCategoryResponse.setCategoryName(budgetCategories.getCategoryName());
        createCategoryResponse.setAmount(budgetCategories.getAmount());
        createCategoryResponse.setCategoryId(budgetCategories.getCategoryId());
        createCategoryResponse.setMonthlyBudgetId(budgetCategories.getMonthlyBudgetId().getMonthlyBudgetId());
        return createCategoryResponse;
    }
}
