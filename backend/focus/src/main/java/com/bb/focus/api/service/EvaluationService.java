package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluationItemInfoReq;
import com.bb.focus.api.response.EvaluationSheetResultRes;
import com.bb.focus.db.entity.applicant.Status;
import com.bb.focus.db.entity.interview.InterviewRoom;

import java.util.List;

public interface EvaluationService {


  boolean LoggingUserPass(Long processId,Long applicantId,  Status status);
  boolean ApplicantEvaluation(EvaluationItemInfoReq result, Long ApplicantEvaluatorId, Long evaluationItemId);

  List<EvaluationSheetResultRes> findApplicantEvaluation(Long evaluatorId,Long applicantId,Long interviewId);
//  boolean ModifyApplicantEvaluation(EvaluationResultReq evaluationResultReq);
  boolean UpdateApplicantEvaluationScore(Long ApplicantEvaluatorId);
  boolean UpdateApplicantEvaluationMemo(Long EvaluatorId,String memo);
  boolean createApplicantEvaluator(Long interviewId, InterviewRoom InterviewRoom, Long EvaluatorId,Long ApplicantId); // 평가지 시퀀스 넘버는
}
