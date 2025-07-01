package org.nascotech.take_home_assessment.spec;

import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.nascotech.take_home_assessment.model.FinancialTransaction;
import org.springframework.data.jpa.domain.Specification;


@Conjunction(value = {
        @Or({
                @Spec(path = "userId", params = "searchParam", spec = Equal.class),
                @Spec(path = "service", params = "searchParam", spec = LikeIgnoreCase.class),
                @Spec(path = "status", params = "searchParam", spec = LikeIgnoreCase.class),
                @Spec(path = "reference", params = "searchParam", spec = LikeIgnoreCase.class),
                @Spec(path = "paymentId", params = "searchParam", spec = Equal.class),
        }),
        @Or({
                @Spec(path = "dateCreated", params = {"dateFrom", "dateTo"}, config = "yyyy-MM-dd", spec = Between.class),
        })
})
public interface FinancialTransactionSpec extends Specification<FinancialTransaction> {
}
