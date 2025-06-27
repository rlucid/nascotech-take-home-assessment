package org.nascotech.take_home_assessment.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "financial_transactions")
public class FinancialTransaction {
    @Id
    private Long id;

    private Long userId;
    private String service;
    private String status;
    private String reference;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime modifiedOn;
}
