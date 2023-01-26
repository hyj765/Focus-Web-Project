package com.bb.focus.db.repository;

import com.bb.focus.db.entity.evaluator.Evaluator;
import java.util.List;

public interface EvaluatorCustomRepository {

  List<Evaluator> findAllEvaluatorsByCompanyAdminId(Long companyAdminId);

  Evaluator findEvaluatorByUserId(String userId);
}
