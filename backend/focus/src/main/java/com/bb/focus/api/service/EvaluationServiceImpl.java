package com.bb.focus.api.service;

import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.applicant.ApplicantPassLog;
import com.bb.focus.db.entity.applicant.Status;
import com.bb.focus.db.repository.ApplicantPassLogRepository;
import com.bb.focus.db.repository.ApplicantRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationServiceImpl implements EvaluationService{
  ApplicantPassLogRepository applicantPassLogRepo;
  ApplicantRepository applicantRepo;
  @Autowired
  public EvaluationServiceImpl(ApplicantPassLogRepository applicantPassLogS, ApplicantRepository applicantS){
    applicantPassLogRepo = applicantPassLogS;

    applicantRepo = applicantS;
  }


  public  boolean LoggingUserPass(Long processId,Long applicantId, Status status){
    // 차수 얻고
    ApplicantPassLog applicantPassLog= new ApplicantPassLog();
    Applicant applicant=applicantRepo.findById(applicantId).orElseThrow(IllegalAccessError::new);
    applicant.addApplicantPasslog(applicantPassLog);

    if(!applicantPassLog.setApplicantData(applicant)) {
      return false;
    }

    if(status == Status.P){
      byte currentPassValue = (byte)(applicant.getPass()+1);
      applicant.setPass(currentPassValue);
    }

    applicantPassLog.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

    return true;
  }

}
