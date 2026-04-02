package com.budgetapplication.budgetapp.utils;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import com.budgetapplication.budgetapp.data.models.BudgetTemplate;
import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import com.budgetapplication.budgetapp.data.models.Users;
import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.response.*;

import java.math.BigDecimal;
import java.util.List;

public class Mapper {
    public static BudgetCategories maprequestToCategory(CreateCategoryRequest createCategories, MonthlyBudget budgetId) {
        BudgetCategories budgetCategories = new BudgetCategories();
        budgetCategories.setCategoryName(createCategories.getCategoryName());
        budgetCategories.setAmount(createCategories.getAmount());
        budgetCategories.setMonthlyBudgetId(budgetId);
        budgetCategories.setCategoryType(createCategories.getCategoryType());
        return  budgetCategories;
    }

    public static CreateCategoryResponse mapBudgetCategoryToResponse(BudgetCategories budgetCategories) {
        CreateCategoryResponse createCategoryResponse = new CreateCategoryResponse();
        createCategoryResponse.setCategoryName(budgetCategories.getCategoryName());
        createCategoryResponse.setAmount(budgetCategories.getAmount());
        createCategoryResponse.setCategoryId(budgetCategories.getCategoryId());
        createCategoryResponse.setMonthlyBudgetId(budgetCategories.getMonthlyBudgetId().getMonthlyBudgetId());
        return createCategoryResponse;
    }

    public static ViewCategory MapListOfCategoriesToResponse(List<BudgetCategories> budgetCategoriesList) {
        ViewCategory viewCategory = new ViewCategory();
        viewCategory.setBudgetCategoriesList(budgetCategoriesList);
        return  viewCategory;
    }

    public static DeleteResponse MapDeleteToResponse(BudgetCategories budgetCategories) {
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("CATEGORY "+budgetCategories.getCategoryName()+" HAS BEEN DELETED");
        return  deleteResponse;
    }

    public static MonthlyBudget mapRequestToMonthlyBudget(CreateMonthlyBudgetRequest createMonthlyBudgetRequest, Users users) {
        MonthlyBudget  monthlyBudget = new MonthlyBudget();
        monthlyBudget.setYear(createMonthlyBudgetRequest.getYear());
        monthlyBudget.setIncome(createMonthlyBudgetRequest.getIncome());
        monthlyBudget.setUserId(users);
        monthlyBudget.setBudgetTemplateId(createMonthlyBudgetRequest.getBudgetTemplate());
        return  monthlyBudget;
    }

    public static CreateMonthlyBudgetResponse mapMonthlyBudgetToResponse(MonthlyBudget monthlyBudget) {
        CreateMonthlyBudgetResponse  createMonthlyBudgetResponse = new CreateMonthlyBudgetResponse();
        createMonthlyBudgetResponse.setCreatedAt(monthlyBudget.getCreatedAt());
        createMonthlyBudgetResponse.setUpdatedAt(monthlyBudget.getUpdatedAt());
        createMonthlyBudgetResponse.setMonthlyBudgetId(monthlyBudget.getMonthlyBudgetId());
        createMonthlyBudgetResponse.setIncome(monthlyBudget.getIncome());
        createMonthlyBudgetResponse.setYear(monthlyBudget.getYear());
        createMonthlyBudgetResponse.setMonth(monthlyBudget.getMonth());
        createMonthlyBudgetResponse.setTemplateId(monthlyBudget.getBudgetTemplateId());
        return  createMonthlyBudgetResponse;
    }

    public static DeleteResponse MapDeleteToResponse(MonthlyBudget monthlyBudget) {
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("MONTHLY BUDGET FOR "+ monthlyBudget.getMonth()+","+ monthlyBudget.getYear()+" HAS BEEN DELETED");
        return deleteResponse;
    }

    public static TotalExpenseResponse mapExpenseToResponse(BigDecimal sumOfExpense,MonthlyBudget monthlyBudget) {
        TotalExpenseResponse totalExpenseResponse = new TotalExpenseResponse();
        totalExpenseResponse.setMessage("TOTAL EXPENSE FOR THE MONTH OF "+monthlyBudget.getMonth()+" "+ "IS "+sumOfExpense.toString());
        return  totalExpenseResponse;
    }

    public static TotalSavingsResponse mapTotalSavingsResponseToSumOfSavings(BigDecimal sumOfSavings, MonthlyBudget monthlyBudget) {
        TotalSavingsResponse totalSavingsResponse = new TotalSavingsResponse();
        totalSavingsResponse.setMessage("TOTAL SAVINGS FOR THE MONTH OF "+monthlyBudget.getMonth()+" IS "+sumOfSavings.toString());
        return  totalSavingsResponse;
    }

    public static TotalRemainingBalance mapMonthlyBudgetGetBalanceToResponse(MonthlyBudget monthlyBudget, BigDecimal sumOfExpense) {
        TotalRemainingBalance totalRemainingBalance = new TotalRemainingBalance();
        totalRemainingBalance.setMessage("TOTAL BALANCE FOR "+monthlyBudget.getMonth()+" IS "+sumOfExpense.toString());
        return  totalRemainingBalance;

    }

    public static void mapBudgetTemplate(CreateMonthlyBudgetRequest createMonthlyBudgetRequest) {

    }

    public static BudgetTemplate setTemplateFields(CreateMonthlyBudgetRequest createMonthlyBudgetRequest, Users userId) {
        BudgetTemplate budgetTemplate = new BudgetTemplate();
        budgetTemplate.setYear(createMonthlyBudgetRequest.getYear());
        budgetTemplate.setUser(userId);
        return budgetTemplate;
    }

    public static CreateTemplateResponse mapCreateTemplateResponse(List<CreateMonthlyBudgetResponse> monthlyBudgetResponseList, BudgetTemplate budgetTemplate) {
        CreateTemplateResponse createTemplateResponse = new CreateTemplateResponse();
        createTemplateResponse.setMonthlyBudgets(monthlyBudgetResponseList);
        createTemplateResponse.setYear(budgetTemplate.getYear());
        createTemplateResponse.setTemplateId(budgetTemplate.getBudgetTemplateId());
        return createTemplateResponse;
    }

    public static EditTemplateResponse mapEditTemplateREsponse(BudgetTemplate budgetTemplate) {
        EditTemplateResponse  editTemplateResponse = new EditTemplateResponse();
        editTemplateResponse.setMessage("TEMPLATE YEAR CHANGED TO "+budgetTemplate.getYear());
        return  editTemplateResponse;
    }
}
