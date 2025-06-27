package org.nascotech.take_home_assessment.dto;

import java.util.List;
import java.util.Map;


public record DataListPaymentResponse(List<PaymentResponse> payments, Map<String, String> _links) {
}

