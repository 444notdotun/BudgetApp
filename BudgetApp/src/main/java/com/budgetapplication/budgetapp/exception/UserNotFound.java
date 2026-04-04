package com.budgetapplication.budgetapp.exception;

public class UserNotFound extends BudgetAppException {
    public UserNotFound(String userNotFound) {
        super(userNotFound);
    }
}
