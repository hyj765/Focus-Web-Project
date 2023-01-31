package com.bb.focus.api.controller;

import com.bb.focus.api.request.InterviewReq;
import com.bb.focus.api.service.InterviewService;
import com.bb.focus.common.model.response.BaseResponseBody;
import com.bb.focus.db.entity.interview.Interview;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.entity.interview.Room;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interview/round")
public class InterviewController {

  private final InterviewService interviewService;

  @ApiOperation(value = "면접(N차) 생성", notes = "프로세스에 n차 면접을 생성한다. 평가지 매핑 포함")
  @PostMapping("/{process-id}")
  public ResponseEntity<?> createInterview(
      @ApiIgnore Authentication authentication,
      @PathVariable("process-id") Long processId,
      @RequestBody @ApiParam(value = "면접 생성 정보", required = true) List<InterviewReq> interviewReq) {

    for(InterviewReq interview : interviewReq){
      interviewService.createInterview(processId, interview);
    }
    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }


  @ApiOperation(value = "해당 면접(N차)에 만들어진 룸 리스트 조회")
  @GetMapping("/rooms")
  public ResponseEntity<?> getRoomList(
          @RequestBody @ApiParam(value = "면접(N차) 시퀀스 넘버", required = true) Long interviewId) {

    Interview interview = interviewService.findInterviewById(interviewId);
    List<Room> result = interview.getRoomList();

    return ResponseEntity.status(200).body(result);
  }


}
