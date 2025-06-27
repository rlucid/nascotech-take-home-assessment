package org.nascotech.take_home_assessment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nascotech.take_home_assessment.client.PaymentServiceClient;
import org.nascotech.take_home_assessment.dto.DataListPaymentResponse;
import org.nascotech.take_home_assessment.dto.PaymentDto;
import org.nascotech.take_home_assessment.model.FinancialTransaction;
import org.nascotech.take_home_assessment.repo.FinancialTransactionRepository;
import org.nascotech.take_home_assessment.spec.FinancialTransactionSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class FinancialTransactionServiceImplTest {

    @Mock
    FinancialTransactionRepository repository;

    @Mock
    PaymentServiceClient paymentServiceClient;

    @InjectMocks
    FinancialTransactionServiceImpl service;


    @Test
    void testFilterFinancialTransactionsReturnsSortedList() throws Exception {
        FinancialTransaction financialTransaction = new FinancialTransaction();
        financialTransaction.setId(1L);
        financialTransaction.setPaymentId(100L);

        Page<FinancialTransaction> pagedFinancialTransactions = new PageImpl<>(List.of(financialTransaction));
        Mockito.when(
                repository.findAll((FinancialTransactionSpec) Mockito.any(), (Pageable) Mockito.any())
        ).thenReturn(pagedFinancialTransactions);

        LocalDateTime dummyDateTime = LocalDateTime.now();
        PaymentDto paymentDto = new PaymentDto(100L, BigDecimal.TEN, "SUCCESS", dummyDateTime, dummyDateTime);
        Mockito.when(paymentServiceClient.retrievePaymentDetails(100L))
                .thenReturn(Mono.just(paymentDto));

        Mono<ResponseEntity<DataListPaymentResponse>> result = service.filterFinancialTransactions(
                Mockito.mock(FinancialTransactionSpec.class),
                PageRequest.of(0, 10)
        );

        StepVerifier.create(result)
                .assertNext(response -> {
                    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
                    Assertions.assertNotNull(response.getBody());
                    Assertions.assertEquals(1, response.getBody().payments().size());
                })
                .verifyComplete();
    }
}
