package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluationItemInfoReq;
import com.bb.focus.api.response.EvaluationSheetResultRes;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.applicant.ApplicantPassLog;
import com.bb.focus.db.entity.applicant.Status;
import com.bb.focus.db.entity.evaluation.EvaluationItem;
import com.bb.focus.db.entity.evaluation.EvaluationResult;
import com.bb.focus.db.entity.evaluation.EvaluationSheet;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.helper.ApplicantEvaluator;
import com.bb.focus.db.entity.interview.Interview;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.entity.process.Process;
import com.bb.focus.db.repository.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationServiceImpl implements EvaluationService{
  ApplicantPassLogRepository applicantPassLogRepo;
  ApplicantRepository applicantRepo;
  ProcessRepository processRepo;
  ApplicantEvaluatorRepository applicantEvaluatorRepo;
  EvaluationSheetItemRepository evaluationSheetItemRepo;
  EvaluationSheetRepository evaluationSheetRepo;
  EvaluationResultRepository evaluationResultRepo;
  InterviewRepository interviewRepo;
  EvaluatorRepository evaluatorRepo;

  @Autowired
  public EvaluationServiceImpl(ApplicantPassLogRepository applicantPassLogRepository
      , ProcessRepository processRepository
      , ApplicantRepository applicantRepository
      , ApplicantEvaluatorRepository applicantEvaluatorRepository
      , EvaluationSheetItemRepository evaluationSheetItemRepository
      , EvaluationResultRepository evaluationResultRepository
      , InterviewRepository interviewRepository
      , EvaluatorRepository evaluatorRepository
      , EvaluationSheetRepository evaluationSheetRepository
      )
  {

    applicantPassLogRepo = applicantPassLogRepository;
    processRepo = processRepository;
    applicantRepo = applicantRepository;
    applicantEvaluatorRepo = applicantEvaluatorRepository;
    evaluationSheetItemRepo = evaluationSheetItemRepository;
    evaluationResultRepo = evaluationResultRepository;
    interviewRepo = interviewRepository;
    evaluatorRepo = evaluatorRepository;
    evaluationSheetRepo = evaluationSheetRepository;
  }

  // 면접평가 시에 해당 데이터를 저장하는 함수
  public boolean ApplicantEvaluation(EvaluationItemInfoReq result, Long applicantEvaluatorId, Long evaluationItemId){
    ApplicantEvaluator applicantEvaluator=applicantEvaluatorRepo.findById(applicantEvaluatorId).orElseThrow(IllegalAccessError::new);
    List<EvaluationResult> evaluationResultList=applicantEvaluator.getEvaluationResultList();

    if(evaluationResultList != null) {
      for (EvaluationResult evaluationResult : evaluationResultList) {
        if (evaluationResult.getEvaluationItem().getId() == evaluationItemId) {
          evaluationResult.setContent(result.getContent());
          evaluationResult.setScore(result.getScore());
          evaluationResultRepo.save(evaluationResult);
          return true;
        }
      }
    }

    EvaluationItem evaluationItem = evaluationSheetItemRepo.findById(evaluationItemId).orElseThrow(IllegalAccessError::new);
    EvaluationResult evaluationResult = new EvaluationResult();
    evaluationResult.setContent(result.getContent());
    evaluationResult.setScore(result.getScore());
    applicantEvaluator.addEvaluationResult(evaluationResult);
    evaluationResult.setEvaluationItem(evaluationItem);
    evaluationResultRepo.save(evaluationResult);

    return true;
  }


  @Override
  public boolean UpdateApplicantEvaluationMemo(Long applicantEvaluatorId,String memo) {
    ApplicantEvaluator applicantEvaluator=applicantEvaluatorRepo.findById(applicantEvaluatorId).orElseThrow(IllegalAccessError::new);
    applicantEvaluator.setMemo(memo);
    applicantEvaluatorRepo.save(applicantEvaluator);
    return false;
  }

  // 면접 합격 결과를 로그로 찍는 함수. 결과에 들어갈 필요가 있음.
  public  boolean LoggingUserPass(Long processId,Long applicantId, Status status){
    // 로그 생성 후 -> process -> applicant -> interview를 통하여 각자 데이터 추출 이 때 status가 p라면 applicant의 현재 합격 여부 +1;
    ApplicantPassLog applicantPassLog= new ApplicantPassLog();
    Applicant applicant=applicantRepo.findById(applicantId).orElseThrow(IllegalAccessError::new);
    applicant.addApplicantPasslog(applicantPassLog);

    Process process =processRepo.findById(processId).orElseThrow(IllegalAccessError::new);

    if(!applicantPassLog.setApplicantData(applicant)) {
      return false;
    }

    if(!applicantPassLog.setProcess(process)){
      return false;
    }

    if(status == Status.P && process.getCurrentStep() != process.getInterviewCount()){
      byte currentPassValue = (byte)(applicant.getPass()+1);
      applicant.setPass(currentPassValue);
    }

    applicantPassLog.setStatus(status);
    List<Interview> interviewList =process.getInterviewList();

    for(Interview interview :interviewList){
      if(interview.getStep() == process.getCurrentStep()){
        applicantPassLog.setInterviewName(interview.getName());

        break;
      }
    }

    applicantPassLog.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
    byte CurStep = (byte)(process.getCurrentStep()+1);

    if(process.getInterviewCount() != process.getCurrentStep()) {
       process.setCurrentStep(CurStep);
    }
    applicantPassLogRepo.save(applicantPassLog);
    return true;
  }

  public List<EvaluationSheetResultRes> findApplicantEvaluation(Long evaluatorId,Long applicantId,Long interviewId){
    ApplicantEvaluator applicantEvaluator = applicantEvaluatorRepo.findByEvaluatorIdAndApplicantIdAndInterviewId(evaluatorId,applicantId,interviewId);
    List<EvaluationSheetResultRes> evaluationSheetResultResList = new ArrayList<>();
    for(EvaluationResult evaluationResult:applicantEvaluator.getEvaluationResultList()){
          EvaluationSheetResultRes evaluationResultRes = new EvaluationSheetResultRes(evaluationResult);
          evaluationSheetResultResList.add(evaluationResultRes);
    }


    return evaluationSheetResultResList;
  }
//  public boolean ModifyApplicantEvaluation(EvaluationResultReq evaluationResultReq){
//    EvaluationResult evaluationResult =evaluationResultRepo.findById(evaluationResultReq.getEvaluationResultId()).orElseThrow(IllegalAccessError::new);
//
//    if(evaluationResult == null) {
//      return false;
//    }
//    evaluationResult.setContent(evaluationResultReq.getContent());
//    evaluationResult.setScore(evaluationResultReq.getScore());
//    evaluationResultRepo.save(evaluationResult);
//
//    return true;
//
//  }

  public boolean createApplicantEvaluator(Long interviewId,InterviewRoom interviewRoom, Long evaluatorId,Long applicantId){
    ApplicantEvaluator applicantEvaluator = new ApplicantEvaluator();

    Interview interview= interviewRepo.findInterviewById(interviewId);
    Evaluator evaluator = evaluatorRepo.findEvaluatorById(evaluatorId);
    Applicant applicant = applicantRepo.findApplicantById(applicantId);

    EvaluationSheet evaluationSheet = evaluationSheetRepo.findById(interview.getEvaluationSheet().getId()).orElseThrow(IllegalAccessError::new);
    interview.setApplicantEvaluator(applicantEvaluator);
    evaluator.setApplicantEvaluator(applicantEvaluator);
    applicantEvaluator.setApplicant(applicant);
    applicantEvaluator.setEvaluationSheet(evaluationSheet);
    applicantEvaluator.setInterviewRoom(interviewRoom);

    applicantEvaluatorRepo.save(applicantEvaluator);

    return true;
  }

  // 총점 재계산하는 함수
  public boolean UpdateApplicantEvaluationScore(Long ApplicantEvaluatorId){
    List<EvaluationResult> evaluationResultList=evaluationResultRepo.findByapplicantEvaluatorId(ApplicantEvaluatorId);
    int total =0;
    for(EvaluationResult evaluationResult:evaluationResultList){
        total +=evaluationResult.getScore();
    }
    ApplicantEvaluator applicantEvaluator=applicantEvaluatorRepo.findById(ApplicantEvaluatorId).orElseThrow(IllegalAccessError::new);
    applicantEvaluator.setScore(total);
    applicantEvaluatorRepo.save(applicantEvaluator);
    return true;
  }


}
