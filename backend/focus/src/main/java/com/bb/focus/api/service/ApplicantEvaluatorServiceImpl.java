package com.bb.focus.api.service;

import com.bb.focus.api.request.AddRemoveApplicantReq;
import com.bb.focus.api.request.AddRemoveEvaluatorReq;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.helper.ApplicantEvaluator;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.repository.ApplicantEvaluatorRepository;
import com.bb.focus.db.repository.EvaluatorRepository;
import com.bb.focus.db.repository.InterviewRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicantEvaluatorServiceImpl implements ApplicantEvaluatorService {

  private final ApplicantEvaluatorRepository applicantEvaluatorRepository;
  private final InterviewRoomRepository interviewRoomRepository;
  private final EvaluatorRepository evaluatorRepository;

  @Transactional
  public void mappingEvaluatorWithApplicants(AddRemoveEvaluatorReq addRemoveEvaluatorReq) {

    ApplicantEvaluator applicantEvaluator = new ApplicantEvaluator();

    InterviewRoom interviewRoom = interviewRoomRepository
        .findById(addRemoveEvaluatorReq.getInterviewRoomId())
        .orElseThrow(IllegalArgumentException::new);

    Evaluator evaluator = evaluatorRepository
        .findById(addRemoveEvaluatorReq.getEvaluatorId())
        .orElseThrow(IllegalArgumentException::new);

    //applicant
    //interview
    //evaluationSheet

  }

  @Transactional
  public void mappingApplicantWithEvaluators(AddRemoveApplicantReq addRemoveApplicantReq) {

  }
}
