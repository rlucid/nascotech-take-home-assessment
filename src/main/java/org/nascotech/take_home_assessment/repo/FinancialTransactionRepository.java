package org.nascotech.take_home_assessment.repo;

import org.nascotech.take_home_assessment.model.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
}
