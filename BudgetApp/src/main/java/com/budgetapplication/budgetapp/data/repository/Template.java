package com.budgetapplication.budgetapp.data.repository;

import com.budgetapplication.budgetapp.data.models.BudgetTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Template extends JpaRepository<BudgetTemplate, String> {
}
