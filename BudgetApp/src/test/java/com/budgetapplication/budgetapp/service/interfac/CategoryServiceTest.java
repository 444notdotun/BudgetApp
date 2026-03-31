package com.budgetapplication.budgetapp.service.interfac;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import com.budgetapplication.budgetapp.data.models.Users;
import com.budgetapplication.budgetapp.data.repository.CategoriesRepository;
import com.budgetapplication.budgetapp.data.repository.MonthlyBudgetRepository;
import com.budgetapplication.budgetapp.data.repository.UserRepository;
import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.EditCategory;
import com.budgetapplication.budgetapp.dtos.response.CreateCategoryResponse;
import com.budgetapplication.budgetapp.exception.MonthlyBudgetDoesNotExistException;
import com.budgetapplication.budgetapp.service.implementation.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceTest {
    CreateCategoryRequest createCategoryRequest;
    String monthlyBudgetId;
    MonthlyBudget  monthlyBudget;
    EditCategory  editCategory;
    String  categoryId;


    @Autowired
    CategoryService categoryService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    MonthlyBudgetRepository monthlyBudgetRepository;

    @BeforeEach
    void setUp() {
        Users users =  new Users();
        userRepository.save(users);
        createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setCategoryName("TRANSPORTATION");
        createCategoryRequest.setAmount(new BigDecimal("100000"));
        monthlyBudget = new MonthlyBudget();
        monthlyBudget.setYear(2018);
        monthlyBudget.setMonth(Month.JANUARY);
        monthlyBudget.setIncome(new BigDecimal("1070000"));
        monthlyBudget.setUserId(users);
        monthlyBudgetRepository.save(monthlyBudget);
        userRepository.save(users);
        monthlyBudgetId=monthlyBudget.getMonthlyBudgetId();
        editCategory = new EditCategory();
        editCategory.setCategoryName("FEEDING");

    }





    @Test
    void testThatBudgetCategoryCanBeCreated(){
        CreateCategoryResponse createCategoryResponse = categoryService.createCategories(createCategoryRequest,monthlyBudgetId);
        assertNotNull(createCategoryResponse);
        assertEquals(monthlyBudgetId,createCategoryResponse.getMonthlyBudgetId());
       assertTrue(categoriesRepository.existsByCategoryId(createCategoryResponse.getCategoryId()));
        categoryId=createCategoryResponse.getCategoryId();
    }

    @Test
    void testThatBudgetCategoryCanNotBeCreatedWithOutBudget(){
        monthlyBudgetId=null;
        assertThrows(MonthlyBudgetDoesNotExistException.class,() -> categoryService.createCategories(createCategoryRequest,monthlyBudgetId));
    }

    @Test
    void testThatBudgetCategoryNameCanBeEdited(){
        testThatBudgetCategoryCanBeCreated();
        CreateCategoryResponse createCategoryResponse = categoryService.editCategories(editCategory,categoryId);
        assertNotNull(createCategoryResponse);
        assertEquals(categoriesRepository.findByCategoryId(categoryId).getCategoryName(),createCategoryResponse.getCategoryName());
    }

    @Test
    void testThatBudgetCategoriesAmountCanBeEdited(){
        testThatBudgetCategoryCanBeCreated();
        editCategory.setAmount(new BigDecimal("90500.00"));
        editCategory.setCategoryName(null);
        CreateCategoryResponse createCategoryResponse = categoryService.editCategories(editCategory,categoryId);
        assertNotNull(createCategoryResponse);
        assertEquals(categoriesRepository.findByCategoryId(categoryId).getAmount(),createCategoryResponse.getAmount());
    }

    @Test
    void testThatBudgetCategoriesFieldsCanBeEdited(){
        testThatBudgetCategoryCanBeCreated();
        editCategory.setAmount(new BigDecimal("90500.00"));
        editCategory.setCategoryName("RENT");
        CreateCategoryResponse createCategoryResponse = categoryService.editCategories(editCategory,categoryId);
        assertNotNull(createCategoryResponse);
        assertEquals(categoriesRepository.findByCategoryId(categoryId).getAmount(),createCategoryResponse.getAmount());
        assertEquals(categoriesRepository.findByCategoryId(categoryId).getCategoryName(),createCategoryResponse.getCategoryName());
    }

//    @Test
//    void testThatBudgetCategoriesCanBeViewed(){
//        testThatBudgetCategoryCanBeCreated();
//        ViewCategory viewCategory =
//    }



}