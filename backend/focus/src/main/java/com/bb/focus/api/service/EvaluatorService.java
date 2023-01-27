package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluatorInfoReq;
import com.bb.focus.db.entity.evaluator.Evaluator;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EvaluatorService {

  //평가자 계정 생성
  public Long create(Long companyAdminId, EvaluatorInfoReq evaluatorInfoReq);

  //ID, PWD 자동생성
  public void autoAssignAccount(Long id);

  //평가자 계정 기본정보 수정 (이름, 사번, 부서, 직급, 전화번호, 이메일, 사진)
  public void updateEvaluatorInfo(Long id, EvaluatorInfoReq evaluatorInfoReq);

  public void removeEvaluator(Long id);

  public Page<Evaluator> findAllEvaluatorsUsePaging(Pageable pageable, Long companyAdminId);

  public List<Evaluator> findAllEvaluators(Long companyAdminId);

  public Evaluator findEvaluator(Long id);

  public Evaluator getEvaluatorByUserId(String userId);

}
