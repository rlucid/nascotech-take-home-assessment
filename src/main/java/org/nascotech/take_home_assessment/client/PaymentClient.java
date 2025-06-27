package org.nascotech.take_home_assessment.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final WebClient webClient;

    void retrieveFinancialTransaction(Long paymentId) {
        return webClient.get("");
    }
}
