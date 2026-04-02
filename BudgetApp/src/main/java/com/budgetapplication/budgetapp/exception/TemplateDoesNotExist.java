package com.budgetapplication.budgetapp.exception;

public class TemplateDoesNotExist extends BudgetAppException {
    public TemplateDoesNotExist(String templateDoesNot) {
        super(templateDoesNot);
    }
}
