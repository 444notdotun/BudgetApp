package com.budgetapplication.budgetapp.service.implementation;

import com.budgetapplication.budgetapp.data.models.BudgetCategories;
import com.budgetapplication.budgetapp.data.models.MonthlyBudget;
import com.budgetapplication.budgetapp.data.repository.CategoriesRepository;
import com.budgetapplication.budgetapp.data.repository.MonthlyBudgetRepository;
import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.EditCategory;
import com.budgetapplication.budgetapp.dtos.response.CreateCategoryResponse;
import com.budgetapplication.budgetapp.dtos.response.DeleteResponse;
import com.budgetapplication.budgetapp.dtos.response.ViewCategory;
import com.budgetapplication.budgetapp.exception.BudgetCategoryDoesNotExist;
import com.budgetapplication.budgetapp.exception.MonthlyBudgetDoesNotExistException;
import com.budgetapplication.budgetapp.service.interfac.CategoryService;
import com.budgetapplication.budgetapp.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final MonthlyBudgetRepository  monthlyBudgetRepository;
    private final CategoriesRepository categoriesRepository;
    public CategoryServiceImpl( MonthlyBudgetRepository monthlyBudgetRepository, CategoriesRepository categoriesRepository) {
        this.categoriesRepository =categoriesRepository;
        this.monthlyBudgetRepository=monthlyBudgetRepository;

    }




    @Override
    public CreateCategoryResponse createCategories(CreateCategoryRequest createCategories, String budgetId) {
        validateMonthlyBudgetId(budgetId);
        MonthlyBudget monthlyBudget =monthlyBudgetRepository.findByMonthlyBudgetId(budgetId);
        BudgetCategories budgetCategories = Mapper.maprequestToCategory(createCategories,monthlyBudget);
        categoriesRepository.save(budgetCategories);
        log.info("Created Category with id {}",budgetCategories.getCategoryId());
        return Mapper.mapBudgetCategoryToResponse(budgetCategories);
    }

    @Override
    public CreateCategoryResponse editCategories(EditCategory editCategory, String budgetCategory) {
        validateBudgetCategory(budgetCategory);
        BudgetCategories budgetCategories=categoriesRepository.findByCategoryId(budgetCategory);
        BudgetCategories editedBudgetCategories = null;
        if(editCategory.getCategoryName()==null){
             editedBudgetCategories = editAmount(editCategory,budgetCategories);
             log.info("edited Category with amount {}",editedBudgetCategories.getAmount());
        } else if (editCategory.getAmount() == null) {
            editedBudgetCategories = editName(editCategory,budgetCategories);
            log.info("edited Category with name {}",editedBudgetCategories.getCategoryName());
        }else {
            editedBudgetCategories = editFields(editCategory,budgetCategories);
            log.info("edited Category with fields {}",editedBudgetCategories.getCategoryId());
        }
        categoriesRepository.save(editedBudgetCategories);
        return Mapper.mapBudgetCategoryToResponse(editedBudgetCategories);
    }

    @Override
    public ViewCategory viewCategory(String monthlyBudgetId) {
        validateMonthlyBudgetId(monthlyBudgetId);
        List<BudgetCategories> budgetCategoriesList =categoriesRepository.findAllByMonthlyBudgetId_MonthlyBudgetId(monthlyBudgetId);
        log.info("view category");
        return Mapper.MapListOfCategoriesToResponse(budgetCategoriesList);
    }

    @Override
    public DeleteResponse deleteCategory(String monthlyBudgetId, String CategoryId) {
       validateMonthlyBudgetId(monthlyBudgetId);
       validateBudgetCategory(CategoryId);
        BudgetCategories budgetCategories=categoriesRepository.findByCategoryId(CategoryId);
       categoriesRepository.deleteById(CategoryId);
       return Mapper.MapDeleteToResponse(budgetCategories);


    }


    private void  validateMonthlyBudgetId(String budgetId){
        if(!monthlyBudgetRepository.existsByMonthlyBudgetId(budgetId)){
            log.info("Monthly Budget Id doesn't exist");
            throw new MonthlyBudgetDoesNotExistException("monthlyBudget doesn't exist");
        }
    }

    private void  validateBudgetCategory(String budgetCategoryId){
        if(!categoriesRepository.existsByCategoryId(budgetCategoryId)){
            log.info(" BudgetCategoryId doesn't exist");
            throw new BudgetCategoryDoesNotExist("BudgetCategoryId doesn't exist");
        }
    }
    private BudgetCategories editName(EditCategory editCategory,BudgetCategories budgetCategories){
        if(editCategory.getAmount()==null){
            budgetCategories.setCategoryName(editCategory.getCategoryName());
        }
        return  budgetCategories;
    }
    private BudgetCategories editAmount(EditCategory editCategory,BudgetCategories budgetCategories){
        if(editCategory.getCategoryName()==null){
            budgetCategories.setAmount(editCategory.getAmount());
        }
        return  budgetCategories;
    }
    private BudgetCategories editFields(EditCategory editCategory,BudgetCategories budgetCategories){
        if(editCategory.getAmount()!=null&&editCategory.getCategoryName()!=null){
            budgetCategories.setCategoryName(editCategory.getCategoryName());
            budgetCategories.setAmount(editCategory.getAmount());
        }
        return  budgetCategories;
    }
}
