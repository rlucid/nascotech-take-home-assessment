package org.nascotech.take_home_assessment.client;

import lombok.extern.slf4j.Slf4j;
import org.nascotech.take_home_assessment.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * Client to make request to payment service
 */
@Slf4j
@Component
public class PaymentServiceClient {

    private final WebClient webClient;

    public PaymentServiceClient(WebClient.Builder builder, @Value("${payment.service.url}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
        log.info("Initialized payment client with baseUrl {}", baseUrl);
    }

    public Mono<PaymentDto> retrieveFinancialTransaction(Long paymentId) {
        log.info("Retrieving payment for ID {} ", paymentId);
        return webClient.get()
                .uri("/payments/{id}", paymentId)
                .retrieve()
                .bodyToMono(PaymentDto.class)
                .doOnNext(payment -> {
                    log.info("Retrieved payment: {}", payment);
                })
                .doOnError(ex -> {
                    log.error("Failed to retrieve payment for ID {}: {}", paymentId, ex.getMessage());
                    handleError(ex);
                });
    }

    public void handleError(Throwable ex) {
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
}

