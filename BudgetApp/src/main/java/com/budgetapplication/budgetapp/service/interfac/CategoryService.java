package com.budgetapplication.budgetapp.service.interfac;

import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.EditCategory;
import com.budgetapplication.budgetapp.dtos.response.CreateCategoryResponse;
import com.budgetapplication.budgetapp.dtos.response.DeleteResponse;
import com.budgetapplication.budgetapp.dtos.response.ViewCategory;

public interface CategoryService {

    CreateCategoryResponse createCategories(CreateCategoryRequest createCategories,String BudgetId);

    CreateCategoryResponse editCategories(EditCategory editCategory, String budgetId);

    ViewCategory viewCategory(String monthlyBudgetId);

    DeleteResponse deleteCategory(String monthlyBudgetId,String CategoryId);
}
