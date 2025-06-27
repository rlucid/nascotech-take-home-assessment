package org.nascotech.take_home_assessment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class PaymentDto {
    Long id;
    BigDecimal amount;
    String status;
    LocalDateTime dateCreated;
    LocalDateTime dateModified;
}
