package com.budgetapplication.budgetapp.service.interfac;

import com.budgetapplication.budgetapp.data.models.Users;
import com.budgetapplication.budgetapp.data.repository.UserRepository;
import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.request.EditTemplate;
import com.budgetapplication.budgetapp.dtos.response.CreateMonthlyBudgetResponse;
import com.budgetapplication.budgetapp.dtos.response.CreateTemplateResponse;
import com.budgetapplication.budgetapp.dtos.response.DeleteTemplateResponse;
import com.budgetapplication.budgetapp.dtos.response.EditTemplateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BudgetTemplateTest {
    @Autowired
    BudgetTemplateService budgetTemplateService;

    CreateMonthlyBudgetRequest createMonthlyBudgetRequest;
    @Autowired
    MonthlyBudgetService monthlyBudgetService;
    @Autowired
    UserRepository userRepository;
    String templateId;
    String userId;
    EditTemplate editTemplate;

    @BeforeEach
    void setUp() {
        Users users = new Users();
        userRepository.save(users);
        userId=users.getUserId();
        createMonthlyBudgetRequest = new CreateMonthlyBudgetRequest();
        createMonthlyBudgetRequest.setIncome(new BigDecimal("300000.00"));
        createMonthlyBudgetRequest.setYear(2027);
        createMonthlyBudgetRequest.setMonth("January");
        editTemplate = new EditTemplate();
        editTemplate.setYear(2027);

    }


    @Test
    void testThatBudgetTemplateCanBeCreated(){
        CreateTemplateResponse createMonthlyBudgetResponse =budgetTemplateService.createTemplate(createMonthlyBudgetRequest,userId);
        assertNotNull(createMonthlyBudgetResponse);
        templateId=createMonthlyBudgetResponse.getTemplateId();
    }

    @Test
    void testThatBudgetTemplate(){
        testThatBudgetTemplateCanBeCreated();
        EditTemplateResponse createMonthlyBudgetResponse =budgetTemplateService.editTemplate(templateId,editTemplate);
        assertNotNull(createMonthlyBudgetResponse);
        assertEquals("TEMPLATE YEAR CHANGED TO 2027",createMonthlyBudgetResponse.getMessage());
    }

    @Test
    void testThatMonthlyBudgetCanBeDeleted(){
        testThatBudgetTemplateCanBeCreated();
        DeleteTemplateResponse deleteTemplateResponse = budgetTemplateService.deleteTemplate(templateId);
        assertNotNull(deleteTemplateResponse);
    }
}