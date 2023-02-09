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

import io.swagger.annotations.ApiOperation;
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

import javax.transaction.Transactional;

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


  @ApiOperation(value = "자신이 평가한 지원자 조회", notes = "평가자는 이전에 자신이 평가한 지원자 정보를 읽어온다.")
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

  @ApiOperation(value = "평가자의 사용자 평가 기능", notes = "평가 시 사용될 API")
  @Transactional
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
      evaluationService.UpdateApplicantEvaluationScore(applicantEvaluatorId);
    }catch (Exception e){
      return new ResponseEntity<String>("평가내역 저장실패",HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<String>("평가내역 저장완료",HttpStatus.OK);
  }

  @ApiOperation(value = "합불여부 체크", notes = "각 인터뷰 마지막에 합불여부를 결정하는 API")
  @Transactional
  @PostMapping("/decision/pass")
  public ResponseEntity<?> FinishInterview(@RequestBody List<InterviewResultReq> resultReq,@RequestBody Long processId){

    for (int i = 0; i < resultReq.size(); ++i) {
        evaluationService.LoggingUserPass(  processId
                                          , resultReq.get(i).getApplicantId()
                                          , Status.valueOf(resultReq.get(i).getPass()));
    }
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @ApiOperation(value = "통계 테이블 갱신", notes = "현재 존재하는 사용자들에 대한 통계데이터 갱신")
  @Transactional
  @PostMapping("/staticstic/givestatistics")
  public ResponseEntity<?> UpdateStatistic(@RequestBody Long processId){
    Map<String,Integer> major=dataProcessService.UpdateStatisticTable(processId);
    dataProcessService.CreateMajorTable(major,processId);

    return new ResponseEntity<String>("통계데이터 업데이트 성공",HttpStatus.OK);
  }


  @ApiOperation(value = "평가내역 수정함수", notes = "평가내역 수정함수 decision/pass가 사용되기 전에만 가능")
  @Transactional
  @PutMapping("/modify/evaluation")
  public ResponseEntity<?> ModifyApplicantEvaluation(@RequestBody EvaluationResultReq evaluationResultReq, @RequestBody Long applicantEvaluationId){

    if(!evaluationService.ModifyApplicantEvaluation(evaluationResultReq)){
      return new ResponseEntity<String>("수정에 실패하였습니다.",HttpStatus.BAD_REQUEST);
    }
    if(evaluationService.UpdateApplicantEvaluationScore(applicantEvaluationId)){
      return new ResponseEntity<String>("총점 재계산 실패",HttpStatus.OK);
    }

    return new ResponseEntity<String>("수정 성공",HttpStatus.OK);
  }


  @ApiOperation(value = "평가자의 평가 메모 내용 갱신", notes = "평가자의 평가 메모를 갱신해주는 API")
  @Transactional
  @PutMapping("/save/memo")
  public ResponseEntity<?> EvaluatorSaveMemo(@RequestBody Long applicantEvaluatorId,@RequestBody String memo) {
    // applicantevaluatorId와 메모
    if(!evaluationService.UpdateApplicantEvaluationMemo(applicantEvaluatorId,memo)){
      return new ResponseEntity<String>("메모 갱신 실패",HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<String>("메모 저장 완료",HttpStatus.OK);
  }

}
