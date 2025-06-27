package org.nascotech.take_home_assessment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nascotech.take_home_assessment.client.PaymentServiceClient;
import org.nascotech.take_home_assessment.dto.DataListPaymentResponse;
import org.nascotech.take_home_assessment.dto.PaymentResponse;
import org.nascotech.take_home_assessment.model.FinancialTransaction;
import org.nascotech.take_home_assessment.repo.FinancialTransactionRepository;
import org.nascotech.take_home_assessment.spec.FinancialTransactionSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class FinancialTransactionServiceImpl implements FinancialTransactionService {

    private final FinancialTransactionRepository financialTransactionRepository;
    private final PaymentServiceClient paymentServiceClient;

    @Override
    public Mono<ResponseEntity<DataListPaymentResponse>> filterFinancialTransactions(
            FinancialTransactionSpec financialTransactionSpec,
            Pageable pageable
    ) {
        Page<FinancialTransaction> pagedFinancialTransactions = filter(financialTransactionSpec, pageable);
        Flux<FinancialTransaction> fluxFinancialTransactions = Flux.fromIterable(pagedFinancialTransactions.getContent());
        return fluxFinancialTransactions
                .flatMap(this::retrieveAndBuildPaymentResponse)
                .collectList()
                .map(this::buildDataListResponse)
                .onErrorResume(this::handleError);
    }

    private Page<FinancialTransaction> filter(
            FinancialTransactionSpec financialTransactionSpec,
            Pageable pageable
    ) {
        log.info("Filtering financial transaction with spec {} and pageable {}", financialTransactionSpec, pageable);
        return financialTransactionRepository.findAll(financialTransactionSpec, pageable);
    }

    private Mono<PaymentResponse> retrieveAndBuildPaymentResponse(FinancialTransaction financialTransaction) {
        log.info("Retrieving and building payment response");
        return paymentServiceClient.retrievePaymentDetails(financialTransaction.getPaymentId())
                .map(paymentDto -> new PaymentResponse(financialTransaction, paymentDto));
    }

    private ResponseEntity<DataListPaymentResponse> buildDataListResponse(List<PaymentResponse> paymentResponses) {
        log.info("Sorting payment response list by payment IDs");
        List<PaymentResponse> sortedResponses = paymentResponses.stream()
                .sorted(Comparator.comparing(p -> p.paymentDto().id(), Comparator.reverseOrder()))
                .toList();

        log.info("Building data list payment response");
        DataListPaymentResponse response = new DataListPaymentResponse(sortedResponses, Map.of("self", "/api/payments"));
        return ResponseEntity.ok(response);
    }

    private Mono<ResponseEntity<DataListPaymentResponse>> handleError(Throwable ex) {
        log.error("Error occurred while fetching transactions: {}", ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }
}
