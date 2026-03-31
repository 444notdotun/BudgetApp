package com.budgetapplication.budgetapp.data.models;

import com.budgetapplication.budgetapp.utils.IdGenerator;
import com.budgetapplication.budgetapp.utils.Type;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity(name = "BudgetCategories")
@Data
public class BudgetCategories {
    @Id
    private String categoryId;
    private String categoryName;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "monthlyBudgetId")
    private MonthlyBudget monthlyBudgetId;

    @PrePersist
    public void prePersist()
    {
        this.categoryId=IdGenerator.generate(Type.CATEGORY);
    }
}
