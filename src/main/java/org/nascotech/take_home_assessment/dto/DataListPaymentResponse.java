package org.nascotech.take_home_assessment.dto;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
public class DataListPaymentResponse {
    List<PaymentResponse> payments;
    Map<String, String> links;
}
