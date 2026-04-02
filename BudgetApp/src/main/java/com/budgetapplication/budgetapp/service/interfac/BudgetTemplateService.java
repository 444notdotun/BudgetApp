package com.budgetapplication.budgetapp.service.interfac;

import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.request.EditTemplate;
import com.budgetapplication.budgetapp.dtos.response.CreateMonthlyBudgetResponse;
import com.budgetapplication.budgetapp.dtos.response.CreateTemplateResponse;
import com.budgetapplication.budgetapp.dtos.response.DeleteTemplateResponse;
import com.budgetapplication.budgetapp.dtos.response.EditTemplateResponse;

public interface BudgetTemplateService {
    CreateTemplateResponse createTemplate(CreateMonthlyBudgetRequest createMonthlyBudgetRequest, String userId);

    EditTemplateResponse editTemplate(String templateId, EditTemplate  editTemplate);

    DeleteTemplateResponse deleteTemplate(String templateId);
}
