package com.budgetapplication.budgetapp.data.repository;

import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, String> {
    boolean existsByMonthlyBudgetId(String monthlyBudgetId);
    MonthlyBudget findByMonthlyBudgetId(String monthlyBudgetId);
    List<MonthlyBudget> findAllMonthlyBudgetByUserId_UserId(String userId);
}