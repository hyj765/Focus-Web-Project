package com.bb.focus.api.controller;

import com.bb.focus.api.request.EvaluationData;
import com.bb.focus.api.request.EvaluationResultReq;
import com.bb.focus.api.request.InterviewResultReq;
import com.bb.focus.api.response.EvaluationSheetResultRes;
import com.bb.focus.api.service.EvaluationService;
import com.bb.focus.db.entity.applicant.Status;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "평가 API", tags = {"Evaluation"})
@RestController
@CrossOrigin("*")
@RequestMapping("/interview")
public class EvaluationController {

  EvaluationService evaluationService;

  public EvaluationController(EvaluationService evaluationS){
    evaluationService = evaluationS;
  }


  @GetMapping("/evaluation/{interview-id}/{evaluator-id}/{applicant-id}")
  public ResponseEntity<?> GetApplicantEvaluationInfo(@PathVariable(name="interview-id")Long interviewId,
      @PathVariable(name="evaluator-id")Long evaluatorId
      ,@PathVariable(name="applicant-id")Long applicantId){

    List<EvaluationSheetResultRes> evaluationSheetResultResList=evaluationService.findApplicantEvaluation(evaluatorId,applicantId,interviewId);

    if(evaluationSheetResultResList == null){
      return new ResponseEntity<String>("해당 사용자의 평가정보가 없습니다.",HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<List<EvaluationSheetResultRes>>(evaluationSheetResultResList,HttpStatus.OK);

  }
  @PostMapping("/evaluation")
  public ResponseEntity<?> EvaluationApplicant(
      @RequestBody EvaluationResultReq evaluationResultReq,
      @RequestBody EvaluationData evaluationData)
  {
    evaluationService.ApplicantEvaluation(evaluationResultReq,evaluationData);


    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @PostMapping("/decision/pass")
  public ResponseEntity<?> FinishInterview(@RequestBody List<InterviewResultReq> resultReq,@RequestBody Long processId){

    for (int i = 0; i < resultReq.size(); ++i) {
        evaluationService.LoggingUserPass(  processId
                                          , resultReq.get(i).getApplicantId()
                                          , Status.valueOf(resultReq.get(i).getPass()));
    }



    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @PutMapping("/modify/evaluation")
  public ResponseEntity<?> ModifyApplicantEvaluation(@RequestBody EvaluationResultReq evaluationResultReq){

    if(!evaluationService.ModifyApplicantEvaluation(evaluationResultReq)){
      return new ResponseEntity<String>("수정에 실패하였습니다.",HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<String>("수정 성공",HttpStatus.OK);
  }




}
