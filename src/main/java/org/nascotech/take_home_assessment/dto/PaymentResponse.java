package org.nascotech.take_home_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nascotech.take_home_assessment.model.FinancialTransaction;


@Getter
@AllArgsConstructor
public class PaymentResponse {
    FinancialTransaction financialTransaction;
    PaymentDto paymentDto;
}
