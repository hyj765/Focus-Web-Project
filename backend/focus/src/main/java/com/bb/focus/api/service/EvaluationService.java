package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluationData;
import com.bb.focus.api.request.EvaluationResultReq;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.applicant.Status;
import com.bb.focus.db.repository.ApplicantEvaluatorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface EvaluationService {


  boolean LoggingUserPass(Long processId,Long applicantId,  Status status);
  boolean ApplicantEvaluation(EvaluationResultReq result, EvaluationData evaluationData);

  List<Applicant> findEvaluatorPerApplicant(Long evaluatorId, Long interviewId);
  boolean modifyApplicantEvaluation(Long evaluationResultId,EvaluationResultReq evaluationResultReq);
  boolean updateApplicantEvaluation(Long EvaluatorId);

  boolean createApplicantEvaluator(Long interviewId,Long EvaluatorId,Long ApplicantId); // 평가지 시퀀스 넘버는
}
