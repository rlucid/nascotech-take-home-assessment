package org.nascotech.take_home_assessment.repo;

import org.nascotech.take_home_assessment.model.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long>, JpaSpecificationExecutor<FinancialTransaction>, PagingAndSortingRepository<FinancialTransaction, Long> {
}
