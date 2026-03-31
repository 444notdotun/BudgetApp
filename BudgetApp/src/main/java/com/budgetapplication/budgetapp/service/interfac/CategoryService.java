package com.budgetapplication.budgetapp.service.interfac;

import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.EditCategory;
import com.budgetapplication.budgetapp.dtos.response.CreateCategoryResponse;

public interface CategoryService {

    CreateCategoryResponse createCategories(CreateCategoryRequest createCategories,String BudgetId);

    CreateCategoryResponse editCategories(EditCategory editCategory, String budgetId);
}
