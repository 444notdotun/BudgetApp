package com.budgetapplication.budgetapp.data.repository;

import com.budgetapplication.budgetapp.data.models.BudgetTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetTemplateRepository extends JpaRepository<BudgetTemplate, String> {
 boolean existsByBudgetTemplateId(String  budgetTemplateId);
 BudgetTemplate findByBudgetTemplateId(String budgetTemplateId);
// List<BudgetTemplate> findAllByUserId_userIdAndTemplateId(String userId, String templateId);
}
