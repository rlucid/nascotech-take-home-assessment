package org.nascotech.take_home_assessment.service;

import org.nascotech.take_home_assessment.dto.DataListPaymentResponse;
import org.nascotech.take_home_assessment.spec.FinancialTransactionSpec;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


public interface FinancialTransactionService {
    Mono<ResponseEntity<DataListPaymentResponse>> filterFinancialTransactions(FinancialTransactionSpec expenseSpec, Pageable pageable);
}
