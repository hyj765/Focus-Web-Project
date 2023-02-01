package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluationData;
import com.bb.focus.api.request.EvaluationResultReq;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.applicant.ApplicantPassLog;
import com.bb.focus.db.entity.applicant.Status;
import com.bb.focus.db.entity.evaluation.EvaluationResult;
import com.bb.focus.db.entity.evaluation.EvaluationSheet;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.helper.ApplicantEvaluator;
import com.bb.focus.db.entity.interview.Interview;
import com.bb.focus.db.entity.process.Process;
import com.bb.focus.db.repository.ApplicantEvaluatorRepository;
import com.bb.focus.db.repository.ApplicantPassLogRepository;
import com.bb.focus.db.repository.ApplicantRepository;
import com.bb.focus.db.repository.EvaluationResultRepository;
import com.bb.focus.db.repository.EvaluationSheetItemRepository;
import com.bb.focus.db.repository.EvaluationSheetRepository;
import com.bb.focus.db.repository.EvaluatorRepository;
import com.bb.focus.db.repository.InterviewCustomRepository;
import com.bb.focus.db.repository.ProcessRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
  InterviewCustomRepository interviewCustomRepo;
  EvaluatorRepository evaluatorRepo;

  @Autowired
  public EvaluationServiceImpl(ApplicantPassLogRepository applicantPassLogRepository
      , ApplicantRepository applicantRepository
      , ProcessRepository processRepository
      , ApplicantEvaluatorRepository applicantEvaluatorRepository
      , EvaluationSheetItemRepository evaluationSheetItemRepository
      , EvaluationResultRepository evaluationResultRepository
      , InterviewCustomRepository interviewCustomRepository
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
    interviewCustomRepo = interviewCustomRepository;
    evaluatorRepo = evaluatorRepository;
    evaluationSheetRepo = evaluationSheetRepository;
  }
  public boolean ApplicantEvaluation(EvaluationResultReq result, EvaluationData evaluationData){


    return true;
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

    return true;
  }

  public List<Applicant> findEvaluatorPerApplicant(Long evaluatorId, Long interviewId){
    List<ApplicantEvaluator> applicantEvaluator =applicantEvaluatorRepo.findByEvaluatorId(evaluatorId);


    return null;
  }
  public boolean modifyApplicantEvaluation(Long evaluationResultId,EvaluationResultReq evaluationResultReq){

    EvaluationResult evaluationResult =evaluationResultRepo.findById(evaluationResultId).orElseThrow(IllegalAccessError::new);
    if(evaluationResult == null) {
      return false;
    }

    evaluationResult.setContent(evaluationResultReq.getContent());
    evaluationResult.setScore(evaluationResultReq.getScore());
    return true;

  }

  public boolean createApplicantEvaluator(Long interviewId,Long evaluatorId,Long applicantId){
    ApplicantEvaluator applicantEvaluator = new ApplicantEvaluator();
    Interview interview= interviewCustomRepo.findInterviewById(interviewId);
    Evaluator evaluator = evaluatorRepo.findEvaluatorById(evaluatorId);
    Applicant applicant = applicantRepo.findApplicantById(applicantId);
    EvaluationSheet evaluationSheet = evaluationSheetRepo.findById(interview.getEvaluationSheet().getId()).orElseThrow(IllegalAccessError::new);
    interview.setApplicantEvaluator(applicantEvaluator);
    evaluator.setApplicantEvaluator(applicantEvaluator);
    applicantEvaluator.setApplicant(applicant);
    applicantEvaluator.setEvaluationSheet(evaluationSheet);
    return true;
  }
  public boolean updateApplicantEvaluation(Long ApplicantEvaluatorId){
    List<EvaluationResult> evaluationResultList=evaluationResultRepo.findByapplicantEvaluatorId(ApplicantEvaluatorId);
    int total =0;
    for(EvaluationResult evaluationResult:evaluationResultList){
        total +=evaluationResult.getScore();
    }
    ApplicantEvaluator applicantEvaluator=applicantEvaluatorRepo.findById(ApplicantEvaluatorId).orElseThrow(IllegalAccessError::new);
    applicantEvaluator.setScore(total);
    return true;
  }


}
