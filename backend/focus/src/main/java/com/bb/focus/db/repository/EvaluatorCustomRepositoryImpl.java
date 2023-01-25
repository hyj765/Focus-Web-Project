package com.bb.focus.db.repository;

import com.bb.focus.db.entity.company.QCompanyAdmin;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.evaluator.QEvaluator;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EvaluatorCustomRepositoryImpl implements EvaluatorCustomRepository{

  private final JPAQueryFactory jpaQueryFactory;

  QEvaluator qEvaluator = QEvaluator.evaluator;
  QCompanyAdmin qCompanyAdmin = QCompanyAdmin.companyAdmin;

  public List<Evaluator> findAllEvaluatorsByCompanyAdminId(Long companyAdminId) {
    return jpaQueryFactory
        .selectFrom(qEvaluator)
        .join(qEvaluator.companyAdmin, qCompanyAdmin)
        .where(eqCompanyAdminId(companyAdminId))
        .fetch();
  }

  private BooleanExpression eqCompanyAdminId(Long companyAdminId){
    if(companyAdminId.equals(null)) return null;
    return qCompanyAdmin.id.eq(companyAdminId);
  }
}
