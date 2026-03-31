package com.budgetapplication.budgetapp.data.repository;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<BudgetCategories, String> {
    boolean existsByCategoryId(String categoryId);
    BudgetCategories findByCategoryId(String categoryId);
}
