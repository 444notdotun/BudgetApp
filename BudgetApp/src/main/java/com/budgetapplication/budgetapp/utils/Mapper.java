package com.budgetapplication.budgetapp.utils;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.response.CreateCategoryResponse;
import com.budgetapplication.budgetapp.dtos.response.DeleteResponse;
import com.budgetapplication.budgetapp.dtos.response.ViewCategory;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

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

    public static ViewCategory MapListOfCategoriesToResponse(List<BudgetCategories> budgetCategoriesList) {
        ViewCategory viewCategory = new ViewCategory();
        viewCategory.setBudgetCategoriesList(budgetCategoriesList);
        return  viewCategory;
    }

    public static DeleteResponse MapDeleteToResponse(BudgetCategories budgetCategories) {
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("CATEGORY "+budgetCategories.getCategoryName()+" HAS BEEN DELETED");
        return  deleteResponse;
    }
}
