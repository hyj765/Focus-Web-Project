package com.bb.focus.api.controller;

import com.bb.focus.api.request.AddRemoveApplicantReq;
import com.bb.focus.api.request.AddRemoveEvaluatorReq;
import com.bb.focus.api.request.InterviewRoomReq;
import com.bb.focus.api.service.ApplicantEvaluatorService;
import com.bb.focus.api.service.EvaluationService;
import com.bb.focus.api.service.InterviewRoomService;
import com.bb.focus.api.service.ProcessEvaluatorService;
import com.bb.focus.common.model.response.BaseResponseBody;
import com.bb.focus.db.entity.interview.InterviewRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "면접실 : 면접 일정 API", tags = {"InterviewRoom Schedule"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/interview/schedule")
public class InterviewRoomScheduleController {

  private final InterviewRoomService interviewRoomService;
  private final EvaluationService evaluationService;
  private final ProcessEvaluatorService processEvaluatorService;
  private final ApplicantEvaluatorService applicantEvaluatorService;

  @ApiOperation(value = "면접 일정 생성")
  @PostMapping("/{process-id}")
  public ResponseEntity<?> createInterviewRoom(
      @ApiIgnore Authentication authentication,
      @PathVariable(value = "process-id") Long processId,
      @RequestBody @ApiParam(value = "면접 일정 생성 정보", required = true) InterviewRoomReq interviewRoomReq) {

    InterviewRoom interviewRoom = interviewRoomService.createInterviewRoom(interviewRoomReq);

    Long interviewId = interviewRoomReq.getInterviewId();
    Long[] evaluatorIds = interviewRoomReq.getEvaluators();
    Long[] applicantIds = interviewRoomReq.getApplicants();

    //지원자-평가자 테이블에 데이터 넣기
    for(int e = 0, elen = evaluatorIds.length; e < elen; e++) {
      for (int a = 0, alen = applicantIds.length; a < alen; a++) {
        evaluationService.createApplicantEvaluator(interviewId, interviewRoom, evaluatorIds[e], applicantIds[a]);
      }
    }

    //프로세스 - 평가자 테이블에 데이터 넣기
    for(int e = 0, elen = evaluatorIds.length; e < elen; e++) {
      processEvaluatorService.saveProcessEvaluator(processId, evaluatorIds[e]);
    }

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "면접 일정 수정")
  @PutMapping("/{interview-room-id}")
  public ResponseEntity<Map<String, Long>> updateInterviewRoom(
      @ApiIgnore Authentication authentication,
      @PathVariable(value = "interview-room-id") Long interviewRoomId,
      @RequestBody @ApiParam(value = "면접 일정 수정 정보", required = true) InterviewRoomReq interviewRoomReq) {

    Long id = interviewRoomService.updateInterviewRoom(interviewRoomId, interviewRoomReq);

    Map<String, Long> result = new HashMap<>();
    result.put("id", id);

    return ResponseEntity.status(200).body(result);
  }

  @ApiOperation(value = "면접 일정 삭제")
  @DeleteMapping("/{interview-room-id}")
  public ResponseEntity<?> removeInterviewRoom(
      @ApiIgnore Authentication authentication,
      @PathVariable(value = "interview-room-id") Long interviewRoomId) {

    interviewRoomService.removeInterviewRoom(interviewRoomId);
    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "면접 일정에 단일 평가자 추가")
  @PostMapping("/add/evaluator")
  public ResponseEntity<?> addEvaluator(
      @ApiIgnore Authentication authentication,
      @RequestBody @ApiParam(value = "평가자 추가 요청 정보") AddRemoveEvaluatorReq addEvaluatorReq) {

    interviewRoomService.addEvaluatorToInterviewRoom(addEvaluatorReq.getInterviewRoomId(),
        addEvaluatorReq.getEvaluatorId());

    processEvaluatorService.saveProcessEvaluator(addEvaluatorReq.getProcessId(),
        addEvaluatorReq.getEvaluatorId());

    applicantEvaluatorService.mappingEvaluatorWithApplicants(addEvaluatorReq);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "면접 일정에 단일 지원자 추가")
  @PostMapping("/add/applicant")
  public ResponseEntity<?> addApplicant(
      @ApiIgnore Authentication authentication,
      @RequestBody @ApiParam(value = "지원자 추가 요청 정보") AddRemoveApplicantReq addApplicantReq) {

    interviewRoomService.addApplicantToInterviewRoom(addApplicantReq.getInterviewRoomId(),
        addApplicantReq.getApplicantId());

    applicantEvaluatorService.mappingApplicantWithEvaluators(addApplicantReq);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "면접 일정에서 단일 평가자 삭제")
  @DeleteMapping("/remove/evaluator")
  public ResponseEntity<?> removeEvaluator(
      @ApiIgnore Authentication authentication,
      @RequestBody @ApiParam(value = "평가자 삭제 요청 정보") AddRemoveEvaluatorReq removeEvaluatorReq) {

    interviewRoomService.removeEvaluator(removeEvaluatorReq.getInterviewRoomId(),
        removeEvaluatorReq.getEvaluatorId());

    processEvaluatorService.removeProcessEvaluator(removeEvaluatorReq.getProcessId(),
        removeEvaluatorReq.getEvaluatorId());

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "면접 일정에서 단일 지원자 삭제")
  @DeleteMapping("/remove/applicant")
  public ResponseEntity<?> removeApplicant(
      @ApiIgnore Authentication authentication,
      @RequestBody @ApiParam(value = "지원자 삭제 요청 정보") AddRemoveApplicantReq removeApplicantReq) {

    interviewRoomService.removeApplicant(removeApplicantReq.getInterviewRoomId(),
        removeApplicantReq.getApplicantId());

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }
}
