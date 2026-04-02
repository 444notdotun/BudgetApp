package com.budgetapplication.budgetapp.exception;

public class MonthInputException extends BudgetAppException {
    public MonthInputException(String invalidInput) {
        super(invalidInput);

    }
}
