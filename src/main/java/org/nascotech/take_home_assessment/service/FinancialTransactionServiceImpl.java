package org.nascotech.take_home_assessment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nascotech.take_home_assessment.model.FinancialTransaction;
import org.nascotech.take_home_assessment.repo.FinancialTransactionRepository;
import org.nascotech.take_home_assessment.spec.FinancialTransactionSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class FinancialTransactionServiceImpl implements FinancialTransactionService {

    private final FinancialTransactionRepository financialTransactionRepository;

    @Override
    public Page<FinancialTransaction> retrievePayments(
            FinancialTransactionSpec expenseSpec,
            Pageable pageable
    ) {
        return null;
    }

    @Override
    public Page<FinancialTransaction> filterFinancialTransactions(
            FinancialTransactionSpec financialTransactionSpec,
            Pageable pageable
    ) {
        return financialTransactionRepository.findAll(financialTransactionSpec, pageable);
    }
}
