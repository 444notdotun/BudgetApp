package com.budgetapplication.budgetapp.service.interfac;

import com.budgetapplication.budgetapp.data.models.CategoryType;
import com.budgetapplication.budgetapp.data.models.Users;
import com.budgetapplication.budgetapp.data.repository.MonthlyBudgetRepository;
import com.budgetapplication.budgetapp.data.repository.UserRepository;
import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.request.EditBudgetRequest;
import com.budgetapplication.budgetapp.dtos.response.*;
import com.budgetapplication.budgetapp.exception.MonthInputException;
import com.budgetapplication.budgetapp.exception.MonthlyBudgetException;
import com.budgetapplication.budgetapp.exception.UserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MonthlyBudgetTest {
    @Autowired
    MonthlyBudgetService monthlyBudgetService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MonthlyBudgetRepository monthlyBudgetRepository;

    String userId;
    CreateMonthlyBudgetRequest createMonthlyBudgetRequest;
    String  monthlyBudgetId;
    CreateCategoryRequest  createCategoryRequest;
    EditBudgetRequest editBudgetRequest;

    @BeforeEach
    void setUp() {
        createMonthlyBudgetRequest = new CreateMonthlyBudgetRequest();
        Users users = new Users();
        userRepository.save(users);
        userId=users.getUserId();
        createMonthlyBudgetRequest.setYear(2026);
        createMonthlyBudgetRequest.setMonth("march");
        createMonthlyBudgetRequest.setIncome(new BigDecimal(500000));
        createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setCategoryName("TRANSPORTATION)");
        createCategoryRequest.setAmount(new BigDecimal("200000"));
        createCategoryRequest.setCategoryType(CategoryType.EXPENSE);
        editBudgetRequest = new EditBudgetRequest();
        editBudgetRequest.setMonth(createMonthlyBudgetRequest.getMonth());
        editBudgetRequest.setIncome(new BigDecimal("200000"));
        editBudgetRequest.setYear(2026);

    }


    @Test
    void monthlyBudgetCanBeCreated() {
        CreateMonthlyBudgetResponse createMonthlyBudgetResponse = monthlyBudgetService.createMonthlyBudget(userId,createMonthlyBudgetRequest);
        assertNotNull(createMonthlyBudgetResponse);

        monthlyBudgetId=createMonthlyBudgetResponse.getMonthlyBudgetId();
    }

    @Test
    void testThatMonthlyBudgetCanNotBeCreatedIfUserDetailIsWrong() {
        userId=null;
        assertThrows(UserNotFound.class,() -> monthlyBudgetService.createMonthlyBudget(userId,createMonthlyBudgetRequest));
    }

    @Test
    void testThatMonthlyBudgetCanNotRecreatedIfMonthInputIsBad(){
        createMonthlyBudgetRequest.setMonth("ghs");
        assertThrows(MonthInputException.class,() -> monthlyBudgetService.createMonthlyBudget(userId,createMonthlyBudgetRequest));
    }

    @Test
    void testThatMonthlyBudgetCanBeEdited(){
        monthlyBudgetCanBeCreated();
        editBudgetRequest.setIncome(new BigDecimal(10));
        CreateMonthlyBudgetResponse createMonthlyBudgetResponse = monthlyBudgetService.editMontlyBudget(editBudgetRequest,monthlyBudgetId);
        assertNotNull(createMonthlyBudgetResponse);
        assertEquals(createMonthlyBudgetResponse.getIncome(),editBudgetRequest.getIncome());
    }

    @Test
    void testThatMonthlyBudgetCanYearCanBeEdited(){
        monthlyBudgetCanBeCreated();
        editBudgetRequest.setYear(2028);
        CreateMonthlyBudgetResponse createMonthlyBudgetResponse = monthlyBudgetService.editMontlyBudget(editBudgetRequest,monthlyBudgetId);
        assertNotNull(createMonthlyBudgetResponse);
        assertEquals(createMonthlyBudgetResponse.getYear(),editBudgetRequest.getYear());
    }

    @Test
    void testThatMonthlyBudgetCanNotBeEditedIfMonthInputIsWrong(){
        monthlyBudgetCanBeCreated();
        editBudgetRequest.setMonth("ghs");
        assertThrows(MonthInputException.class,() -> monthlyBudgetService.editMontlyBudget(editBudgetRequest,monthlyBudgetId));
    }

    @Test
    void testThatMonthlyBudgetMonthCanBeEdited(){
        monthlyBudgetCanBeCreated();
        editBudgetRequest.setMonth("JULY");
        CreateMonthlyBudgetResponse createMonthlyBudgetResponse = monthlyBudgetService.editMontlyBudget(editBudgetRequest,monthlyBudgetId);
        assertNotNull(createMonthlyBudgetResponse);
        assertEquals(createMonthlyBudgetResponse.getMonth().toString(),editBudgetRequest.getMonth());
    }

    @Test
    void testThatUserCanViewAllBudget(){
        monthlyBudgetCanBeCreated();
        createMonthlyBudgetRequest.setYear(2029);
        monthlyBudgetCanBeCreated();
        createMonthlyBudgetRequest.setMonth("JULY");
        monthlyBudgetCanBeCreated();
        ViewAllMonthlyBudget viewAllMonthlyBudget = monthlyBudgetService.viewAll(userId);
        assertNotNull(viewAllMonthlyBudget);
        assertEquals(viewAllMonthlyBudget.getMonthlyBudgetList().size(),monthlyBudgetRepository.findAllMonthlyBudgetByUserId_UserId(userId).size());
    }

    @Test
    void testThatMonthlyBudgetCanBeDeleted(){
        monthlyBudgetCanBeCreated();
        DeleteResponse deleteResponse = monthlyBudgetService.deleteMonthlyBudget(userId,monthlyBudgetId);
        assertNotNull(deleteResponse);
        assertEquals("MONTHLY BUDGET FOR MARCH,2026 HAS BEEN DELETED",deleteResponse.getMessage());
    }

    @Test
    void testThatMonthlyBudgetCanNotBeDeletedIfMonthInputIsWrong(){
        assertThrows(MonthlyBudgetException.class,() -> monthlyBudgetService.deleteMonthlyBudget(userId,monthlyBudgetId));
    }

    @Test
    void testThatMonthlyBudgetCanAddCategory(){
        monthlyBudgetCanBeCreated();
        CreateCategoryResponse createCategoryResponse = monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        assertNotNull(createCategoryResponse);
        assertEquals(createCategoryResponse.getCategoryName(),createCategoryRequest.getCategoryName());
        assertEquals(createCategoryResponse.getMonthlyBudgetId(),monthlyBudgetId);
    }

    @Test
    void testThatUserCanGetMonthlyBudgetToTalExpenses(){
        monthlyBudgetCanBeCreated();
       monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        createCategoryRequest.setAmount(new BigDecimal(1000));
        createCategoryRequest.setCategoryName("FEEDING");
        monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        createCategoryRequest.setAmount(new BigDecimal(20000));
        createCategoryRequest.setCategoryName("SCHOOL FEE");
        monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        createCategoryRequest.setCategoryType(CategoryType.SAVINGS);
        createCategoryRequest.setAmount(new BigDecimal(40000));
        createCategoryRequest.setCategoryName("SAVINGS");
        monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        TotalExpenseResponse totalExpenseResponse =monthlyBudgetService.calculateTotalExpense(monthlyBudgetId);
        assertNotNull(totalExpenseResponse);
        assertEquals("TOTAL EXPENSE FOR THE MONTH OF MARCH IS 221000.00",totalExpenseResponse.getMessage());
    }

    @Test
    void testThatUserCanGetMonthlyBudgetToSavings(){
        monthlyBudgetCanBeCreated();
        monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        createCategoryRequest.setAmount(new BigDecimal(1000));
        createCategoryRequest.setCategoryName("FEEDING");
        monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        createCategoryRequest.setAmount(new BigDecimal(20000));
        createCategoryRequest.setCategoryName("SCHOOL FEE");
        monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        createCategoryRequest.setCategoryType(CategoryType.SAVINGS);
        createCategoryRequest.setAmount(new BigDecimal(40000));
        createCategoryRequest.setCategoryName("SAVINGS");
        monthlyBudgetService.addCategory(createCategoryRequest,monthlyBudgetId);
        TotalSavingsResponse totalSavingsResponse = monthlyBudgetService.totalSavings(monthlyBudgetId);
        assertNotNull(totalSavingsResponse);
        assertEquals("TOTAL SAVINGS FOR THE MONTH OF MARCH IS 40000.00",totalSavingsResponse.getMessage());
    }

    @Test
    void testThatUserCanGetMonthlyBudgetToRemaining(){
        monthlyBudgetCanBeCreated();
        testThatUserCanGetMonthlyBudgetToSavings();
        TotalRemainingBalance totalRemainingBalance = monthlyBudgetService.getBalance(monthlyBudgetId);
        assertNotNull(totalRemainingBalance);
        assertEquals("TOTAL BALANCE FOR MARCH IS 239000.00",totalRemainingBalance.getMessage());
    }



}