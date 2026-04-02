package com.budgetapplication.budgetapp.data.repository;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import com.budgetapplication.budgetapp.data.models.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<BudgetCategories, String> {
    boolean existsByCategoryId(String categoryId);
    BudgetCategories findByCategoryId(String categoryId);
    List<BudgetCategories> findAllByMonthlyBudgetId_MonthlyBudgetId(String monthlyBudgetId);
    List<BudgetCategories> findAllByMonthlyBudgetId_MonthlyBudgetIdAndCategoryType(String monthlyBudgetId, CategoryType categoryType);
}
