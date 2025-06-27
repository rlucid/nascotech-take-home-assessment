package org.nascotech.take_home_assessment.service;

import org.nascotech.take_home_assessment.model.FinancialTransaction;
import org.nascotech.take_home_assessment.spec.FinancialTransactionSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FinancialTransactionService {
    Page<FinancialTransaction> retrievePayments(FinancialTransactionSpec expenseSpec, Pageable pageable);
    Page<FinancialTransaction> filterFinancialTransactions(FinancialTransactionSpec expenseSpec, Pageable pageable);
}
