package com.budgetapplication.budgetapp.service.implementation;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import com.budgetapplication.budgetapp.data.models.CategoryType;
import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import com.budgetapplication.budgetapp.data.models.Users;
import com.budgetapplication.budgetapp.data.repository.CategoriesRepository;
import com.budgetapplication.budgetapp.data.repository.MonthlyBudgetRepository;
import com.budgetapplication.budgetapp.data.repository.UserRepository;
import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.response.*;
import com.budgetapplication.budgetapp.exception.MonthInputException;
import com.budgetapplication.budgetapp.exception.MonthlyBudgetException;
import com.budgetapplication.budgetapp.exception.UserNotFoundException;
import com.budgetapplication.budgetapp.service.interfac.CategoryService;
import com.budgetapplication.budgetapp.service.interfac.MonthlyBudgetService;
import com.budgetapplication.budgetapp.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Service
public class MonthlyBudgetImpl implements MonthlyBudgetService {
    @Autowired
    UserRepository  userRepository;
    @Autowired
    MonthlyBudgetRepository monthlyBudgetRepository;
    @Autowired
    CategoriesRepository  categoriesRepository;

    @Autowired
    CategoryService  categoryService;

    @Override
    public CreateMonthlyBudgetResponse createMonthlyBudget(String userId, CreateMonthlyBudgetRequest createMonthlyBudgetRequest) {
        validateUserId(userId);
        Users users = userRepository.findByUserId(userId);
        MonthlyBudget monthlyBudget=Mapper.mapRequestToMonthlyBudget(createMonthlyBudgetRequest,users);
        monthlyBudget.setMonth(validateMonth(createMonthlyBudgetRequest));
        monthlyBudgetRepository.save(monthlyBudget);
        return Mapper.mapMonthlyBudgetToResponse(monthlyBudget);
    }

    @Override
    public CreateMonthlyBudgetResponse editMontlyBudget(CreateMonthlyBudgetRequest createMonthlyBudgetRequest,String monthlyBudgetId) {
        validateMonthlyBudgetId(monthlyBudgetId);
        MonthlyBudget editedMonthlyBudget = monthlyBudgetRepository.findByMonthlyBudgetId(monthlyBudgetId);
        if(createMonthlyBudgetRequest.getIncome()!=null){
            editedMonthlyBudget.setIncome(createMonthlyBudgetRequest.getIncome());
        }
        if(createMonthlyBudgetRequest.getMonth()!=null){
            editedMonthlyBudget.setMonth(validateMonth(createMonthlyBudgetRequest));
        }
        if(createMonthlyBudgetRequest.getYear()!=0){
            editedMonthlyBudget.setYear(createMonthlyBudgetRequest.getYear());
        }
        return Mapper.mapMonthlyBudgetToResponse(monthlyBudgetRepository.save(editedMonthlyBudget));
    }

    @Override
    public ViewAllMonthlyBudget viewAll(String userId) {
        validateUserId(userId);
        ViewAllMonthlyBudget viewAllMonthlyBudget = new ViewAllMonthlyBudget();
        viewAllMonthlyBudget.getMonthlyBudgetList().addAll(monthlyBudgetRepository.findAllMonthlyBudgetByUserId_UserId(userId));
        return viewAllMonthlyBudget;
    }

    @Override
    public DeleteResponse deleteMonthlyBudget(String userId, String monthlyBudgetId) {
        validateUserId(userId);
        validateMonthlyBudgetId(monthlyBudgetId);
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByMonthlyBudgetId(monthlyBudgetId);
        monthlyBudgetRepository.delete(monthlyBudget);
        return Mapper.MapDeleteToResponse(monthlyBudget);
    }

    @Override
    public CreateCategoryResponse addCategory(CreateCategoryRequest createCategoryRequest, String monthlyBudgetId) {
        return categoryService.createCategories(createCategoryRequest,monthlyBudgetId);
    }

    @Override
    public TotalExpenseResponse calculateTotalExpense(String monthlyBudgetId) {
        BigDecimal sumOfExpense=new BigDecimal("0");
        validateMonthlyBudgetId(monthlyBudgetId);
        List<BudgetCategories> budgetCategoriesList= categoriesRepository.findAllByMonthlyBudgetId_MonthlyBudgetIdAndCategoryType(monthlyBudgetId, CategoryType.EXPENSE);
        sumOfExpense = sumUpNumbers(budgetCategoriesList, sumOfExpense);
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByMonthlyBudgetId(monthlyBudgetId);
        return Mapper.mapExpenseToResponse(sumOfExpense,monthlyBudget);
    }

    private  BigDecimal sumUpNumbers(List<BudgetCategories> budgetCategoriesList, BigDecimal sumOfExpense) {
        for(BudgetCategories budgetCategories: budgetCategoriesList){
           sumOfExpense = sumOfExpense.add(budgetCategories.getAmount());

        }
        return sumOfExpense;
    }

    @Override
    public TotalSavingsResponse totalSavings(String monthlyBudgetId) {
        BigDecimal sumOfSavings=new BigDecimal("0");
        validateMonthlyBudgetId(monthlyBudgetId);
        List<BudgetCategories> budgetCategoriesList= categoriesRepository.findAllByMonthlyBudgetId_MonthlyBudgetIdAndCategoryType(monthlyBudgetId, CategoryType.SAVINGS);
        sumOfSavings = sumUpNumbers(budgetCategoriesList, sumOfSavings);
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByMonthlyBudgetId(monthlyBudgetId);
        return Mapper.mapTotalSavingsResponseToSumOfSavings(sumOfSavings,monthlyBudget);

    }

    @Override
    public TotalRemainingBalance getBalance(String monthlyBudgetId) {
        validateMonthlyBudgetId(monthlyBudgetId);
        BigDecimal sumOfExpense=new BigDecimal("0");
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByMonthlyBudgetId(monthlyBudgetId);
        List<BudgetCategories> budgetCategoriesList= categoriesRepository.findAllByMonthlyBudgetId_MonthlyBudgetIdAndCategoryType(monthlyBudgetId, CategoryType.EXPENSE);
        List<BudgetCategories> budgetCategoriesListOfSavings= categoriesRepository.findAllByMonthlyBudgetId_MonthlyBudgetIdAndCategoryType(monthlyBudgetId, CategoryType.SAVINGS);
        sumOfExpense=sumUpNumbers(budgetCategoriesList,sumOfExpense);
        BigDecimal sumOfSavings=new BigDecimal("0");
        sumOfSavings=sumUpNumbers(budgetCategoriesListOfSavings,sumOfSavings);
        BigDecimal remainingExpense=monthlyBudget.getIncome().subtract(sumOfExpense).subtract(sumOfSavings);
        return  Mapper.mapMonthlyBudgetGetBalanceToResponse(monthlyBudget,remainingExpense);

    }

    private void validateMonthlyBudgetId(String monthlyBudgetId) {
        if(!monthlyBudgetRepository.existsByMonthlyBudgetId(monthlyBudgetId)){
            throw new MonthlyBudgetException("monthlyBudget doesn't exist");
        }
    }



    private void validateUserId(String userId) {
        if(!userRepository.existsByUserId(userId)){
            throw  new UserNotFoundException("user not found");
        }
    }

    private Month validateMonth(CreateMonthlyBudgetRequest createMonthlyBudgetRequest) {
        switch (createMonthlyBudgetRequest.getMonth().toLowerCase()){
            case "january"->{return Month.JANUARY;}
            case "february"->{return Month.FEBRUARY;}
            case  "march"->{return Month.MARCH;}
            case "april"->{return Month.APRIL;}
            case "may"->{return Month.MAY;}
            case "june"->{return Month.JUNE;}
            case "july"->{return Month.JULY;}
            case "august"->{return Month.AUGUST;}
            case "september"->{return Month.SEPTEMBER;}
            case "october"->{return Month.OCTOBER;}
            case "november"->{return Month.NOVEMBER;}
            case "december"->{return Month.DECEMBER;}
            default -> {throw new MonthInputException("invalid input");}
        }
    }



}
