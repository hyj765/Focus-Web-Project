package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluationData;
import com.bb.focus.api.request.EvaluationResultReq;
import com.bb.focus.db.entity.applicant.Status;
import com.bb.focus.db.repository.ApplicantEvaluatorRepository;
import org.springframework.stereotype.Service;

@Service
public interface EvaluationService {


  boolean LoggingUserPass(Long processId,Long applicantId,  Status status);
  boolean ApplicantEvaluation(EvaluationResultReq result, EvaluationData evaluationData);

}
