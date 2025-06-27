package org.nascotech.take_home_assessment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nascotech.take_home_assessment.dto.DataListPaymentResponse;
import org.nascotech.take_home_assessment.service.FinancialTransactionService;
import org.nascotech.take_home_assessment.spec.FinancialTransactionSpec;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
public class FinancialTransactionsControllerImpl implements FinancialTransactionsController {

    private final FinancialTransactionService financialTransactionService;

    @Override
    public Mono<ResponseEntity<DataListPaymentResponse>> filterFinancialTransactions(
            FinancialTransactionSpec financialTransactionSpec,
            Pageable pageable
    ) {
        return financialTransactionService.filterFinancialTransactions(financialTransactionSpec, pageable);
    }
}
