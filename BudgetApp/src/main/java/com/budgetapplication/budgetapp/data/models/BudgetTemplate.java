package com.budgetapplication.budgetapp.data.models;

import com.budgetapplication.budgetapp.utils.IdGenerator;
import com.budgetapplication.budgetapp.utils.Type;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class BudgetTemplate {
    @Id
    private String budgetTemplateId;
    private int year;
    @ManyToOne
    @JoinColumn(name = "users")
    private Users user;
    @CreatedDate
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;

    @PrePersist
    public void prePersist()
    {
        this.budgetTemplateId= IdGenerator.generate(Type.BUDGET);
    }
}
