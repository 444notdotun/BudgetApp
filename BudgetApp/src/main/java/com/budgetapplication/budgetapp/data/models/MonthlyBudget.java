package com.budgetapplication.budgetapp.data.models;

import com.budgetapplication.budgetapp.utils.IdGenerator;
import com.budgetapplication.budgetapp.utils.Type;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
@Entity
@Data
public class MonthlyBudget {
    @Id
    private String monthlyBudgetId;
    private Month month;
    private int year;
    private BigDecimal income;
    @ManyToOne
    @JoinColumn(name = "userId" ,nullable = false)
    private Users UserId;
    @ManyToOne
    @JoinColumn(name = "BudgetTemplateId")
    private BudgetTemplate templateId;
    @CreatedDate
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
//    @ManyToOne
//    @JoinColumn(name = "CategoriesId")
//    private Map<String,BudgetCategories>  budgetCategories;

//    public  MonthlyBudget() {
//        this.budgetCategories = new HashMap<>();
//    }
    @PrePersist
    public void prePersist()
    {
        this.monthlyBudgetId= IdGenerator.generate(Type.TEMPLATE);
    }

}
