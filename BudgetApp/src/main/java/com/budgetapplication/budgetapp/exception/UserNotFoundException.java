package com.budgetapplication.budgetapp.exception;

public class UserNotFoundException extends BudgetAppException {
    public UserNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
