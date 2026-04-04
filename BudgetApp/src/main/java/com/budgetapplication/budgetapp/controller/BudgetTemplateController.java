package com.budgetapplication.budgetapp.controller;

import com.budgetapplication.budgetapp.dtos.request.CreateMonthlyBudgetRequest;
import com.budgetapplication.budgetapp.dtos.request.EditTemplate;
import com.budgetapplication.budgetapp.dtos.response.ApiResponse;
import com.budgetapplication.budgetapp.dtos.response.CreateTemplateResponse;
import com.budgetapplication.budgetapp.dtos.response.DeleteTemplateResponse;
import com.budgetapplication.budgetapp.dtos.response.EditTemplateResponse;
import com.budgetapplication.budgetapp.service.interfac.BudgetTemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget-templates")
public class BudgetTemplateController {
@Autowired
    private  BudgetTemplateService budgetTemplateService;



    @PostMapping("")
    public ResponseEntity<ApiResponse<CreateTemplateResponse>> createTemplate(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody CreateMonthlyBudgetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Template created",
                        budgetTemplateService.createTemplate(request, userId)));
    }

    @PatchMapping("/{templateId}")
    public ResponseEntity<ApiResponse<EditTemplateResponse>> editTemplate(
            @AuthenticationPrincipal String userId,
            @PathVariable String templateId,
            @Valid @RequestBody EditTemplate request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Template updated",
                        budgetTemplateService.editTemplate(templateId, request)));
    }

    @DeleteMapping("/{templateId}")
    public ResponseEntity<ApiResponse<DeleteTemplateResponse>> deleteTemplate(
            @AuthenticationPrincipal String userId,
            @PathVariable String templateId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Template deleted",
                        budgetTemplateService.deleteTemplate(templateId)));
    }
}