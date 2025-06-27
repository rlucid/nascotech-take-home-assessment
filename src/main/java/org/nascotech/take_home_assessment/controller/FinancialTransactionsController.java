package org.nascotech.take_home_assessment.controller;

import org.nascotech.take_home_assessment.dto.DataListPaymentResponse;
import org.nascotech.take_home_assessment.spec.FinancialTransactionSpec;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


public interface FinancialTransactionsController {
    Mono<ResponseEntity<DataListPaymentResponse>> filterFinancialTransactions(
            FinancialTransactionSpec financialTransactionSpec,
            Pageable pageable
    );
}
