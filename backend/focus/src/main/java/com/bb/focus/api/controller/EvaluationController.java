package com.bb.focus.api.controller;

import com.bb.focus.api.request.EvaluationResultReq;
import com.bb.focus.api.request.InterviewResultReq;
import com.bb.focus.api.response.EvaluationSheetResultRes;
import com.bb.focus.api.service.DataProcessService;
import com.bb.focus.api.service.EvaluationService;
import com.bb.focus.db.entity.applicant.Status;
import com.bb.focus.db.entity.helper.ApplicantEvaluator;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api/interview")
public class EvaluationController {

  EvaluationService evaluationService;
  DataProcessService dataProcessService;

  public EvaluationController(EvaluationService evaluationS
                             ,DataProcessService dataProcessS){
    evaluationService = evaluationS;
    dataProcessService= dataProcessS;

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
      @RequestBody Long applicantEvaluatorId,
      @RequestBody Long evaluationItemId
      )
  {
    //처음 평가 시에 생성 -> 그 다음에는 해당 면접에서 해당 지원자 넘버가 있을 시 ->
    try {
      evaluationService.ApplicantEvaluation(evaluationResultReq, applicantEvaluatorId, evaluationItemId);
    }catch (Exception e){
      return new ResponseEntity<String>("평가내역 저장실패",HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<String>("평가내역 저장완료",HttpStatus.OK);
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

  @PostMapping("/staticstic/givestatistics")
  public ResponseEntity<?> UpdateStatistic(@RequestBody Long processId){
    Map<String,Integer> major=dataProcessService.UpdateStatisticTable(processId);
    dataProcessService.CreateMajorTable(major,processId);

    return new ResponseEntity<String>("통계데이터 업데이트 성공",HttpStatus.OK);
  }


  @PutMapping("/modify/evaluation")
  public ResponseEntity<?> ModifyApplicantEvaluation(@RequestBody EvaluationResultReq evaluationResultReq){

    if(!evaluationService.ModifyApplicantEvaluation(evaluationResultReq)){
      return new ResponseEntity<String>("수정에 실패하였습니다.",HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<String>("수정 성공",HttpStatus.OK);
  }




}
