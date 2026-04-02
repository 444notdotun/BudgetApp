package com.budgetapplication.budgetapp.service.implementation;

import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import com.budgetapplication.budgetapp.data.repository.BudgetTemplateRepository;
import com.budgetapplication.budgetapp.data.repository.MonthlyBudgetRepository;
import com.budgetapplication.budgetapp.data.repository.UserRepository;
import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.request.EditTemplate;
import com.budgetapplication.budgetapp.dtos.response.CreateMonthlyBudgetResponse;
import com.budgetapplication.budgetapp.dtos.response.CreateTemplateResponse;
import com.budgetapplication.budgetapp.dtos.response.DeleteTemplateResponse;
import com.budgetapplication.budgetapp.dtos.response.EditTemplateResponse;
import com.budgetapplication.budgetapp.exception.TemplateDoesNotExist;
import com.budgetapplication.budgetapp.service.interfac.BudgetTemplateService;
import com.budgetapplication.budgetapp.service.interfac.MonthlyBudgetService;
import com.budgetapplication.budgetapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
@Service
public class BudgetTemplateImpl implements BudgetTemplateService {
    @Autowired
    MonthlyBudgetService  monthlyBudgetService;
    @Autowired
    BudgetTemplateRepository template;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MonthlyBudgetRepository monthlyBudgetRepository;

    @Override
    public CreateTemplateResponse createTemplate(CreateMonthlyBudgetRequest createMonthlyBudgetRequest, String userId) {
        com.budgetapplication.budgetapp.data.models.BudgetTemplate budgetTemplate =Mapper.setTemplateFields(createMonthlyBudgetRequest,userRepository.findByUserId(userId));
        template.save(budgetTemplate);
        createMonthlyBudgetRequest.setBudgetTemplate(budgetTemplate);
        List<CreateMonthlyBudgetResponse> monthlyBudgetResponseList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            createMonthlyBudgetRequest.setMonth(Month.of(i+1).toString());
            monthlyBudgetResponseList.add(monthlyBudgetService.createMonthlyBudget(userId,createMonthlyBudgetRequest));
        }
        return Mapper.mapCreateTemplateResponse(monthlyBudgetResponseList,budgetTemplate);
    }

    @Override
    public EditTemplateResponse editTemplate(String templateId, EditTemplate editTemplate) {
        validateTemplate(templateId);
        com.budgetapplication.budgetapp.data.models.BudgetTemplate budgetTemplate=template.findByBudgetTemplateId(templateId);
        budgetTemplate.setYear(editTemplate.getYear());
        template.save(budgetTemplate);
        return  Mapper.mapEditTemplateREsponse(budgetTemplate);
    }

    @Override
    public DeleteTemplateResponse deleteTemplate(String templateId) {
        validateTemplate(templateId);
        List<MonthlyBudget> monthlyBudgetList = monthlyBudgetRepository.findAllMonthlyBudgetByBudgetTemplateId_BudgetTemplateId(templateId);
        monthlyBudgetRepository.deleteAll(monthlyBudgetList);
        template.delete(template.findByBudgetTemplateId(templateId));
        DeleteTemplateResponse deleteTemplateResponse = new DeleteTemplateResponse();
        deleteTemplateResponse.setMessage(templateId+" deleted");
        return  deleteTemplateResponse;
    }

    private void validateTemplate(String templateId) {
        if (!template.existsByBudgetTemplateId(templateId)) {
            throw new TemplateDoesNotExist("TEMPLATE DOES NOT EXIST");
        }
    }




}
