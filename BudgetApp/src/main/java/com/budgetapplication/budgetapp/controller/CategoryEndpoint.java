package com.budgetapplication.budgetapp.controller;

import com.budgetapplication.budgetapp.dtos.request.CreateCategoryRequest;
import com.budgetapplication.budgetapp.dtos.request.EditCategory;
import com.budgetapplication.budgetapp.dtos.response.ApiResponse;
import com.budgetapplication.budgetapp.dtos.response.CreateCategoryResponse;
import com.budgetapplication.budgetapp.dtos.response.DeleteResponse;
import com.budgetapplication.budgetapp.dtos.response.ViewCategory;
import com.budgetapplication.budgetapp.service.interfac.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryEndpoint {

    @Autowired
    CategoryService  categoryService;
    @PostMapping("/{monthlyBudgetId}/categories")
    public ResponseEntity<ApiResponse<CreateCategoryResponse>> createCategory(
            @AuthenticationPrincipal String userId,
            @PathVariable String monthlyBudgetId,
            @Valid @RequestBody CreateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Category created",
                        categoryService.createCategories(request, monthlyBudgetId)));
    }

    @PatchMapping("/{monthlyBudgetId}/categories/{categoryId}")
    public ResponseEntity<ApiResponse<CreateCategoryResponse>> editCategory(
            @AuthenticationPrincipal String userId,
            @PathVariable String monthlyBudgetId,
            @PathVariable String categoryId,
            @Valid @RequestBody EditCategory request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Category updated",
                        categoryService.editCategories(request, categoryId)));
    }

    @GetMapping("/{monthlyBudgetId}/categories")
    public ResponseEntity<ApiResponse<ViewCategory>> viewCategories(
            @PathVariable String monthlyBudgetId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Categories found",
                        categoryService.viewCategory(monthlyBudgetId)));
    }

    @DeleteMapping("/{monthlyBudgetId}/categories/{categoryId}")
    public ResponseEntity<ApiResponse<DeleteResponse>> deleteCategory(
            @AuthenticationPrincipal String userId,
            @PathVariable String monthlyBudgetId,
            @PathVariable String categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Category deleted",
                        categoryService.deleteCategory(monthlyBudgetId, categoryId)));
    }
}
