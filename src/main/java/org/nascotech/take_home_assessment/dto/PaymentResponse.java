package org.nascotech.take_home_assessment.dto;

import org.nascotech.take_home_assessment.model.FinancialTransaction;


public record PaymentResponse(FinancialTransaction financialTransaction, PaymentDto paymentDto) {
}
