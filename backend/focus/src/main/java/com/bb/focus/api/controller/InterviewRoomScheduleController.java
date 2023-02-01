package com.bb.focus.api.controller;

import com.bb.focus.api.request.InterviewRoomReq;
import com.bb.focus.api.service.InterviewRoomService;
import com.bb.focus.common.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "면접 일정 등록 API", tags = {"CompanyAdmin : InterviewRoom"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/interview/schedule")
public class InterviewRoomScheduleController {

  private final InterviewRoomService interviewRoomService;

  @ApiOperation(value = "면접 일정 생성")
  @PostMapping("")
  public ResponseEntity<?> createInterviewRoom(
      @ApiIgnore Authentication authentication,
      @RequestBody @ApiParam(value = "면접 일정 생성 정보", required = true) InterviewRoomReq interviewRoomReq) {

    interviewRoomService.createInterviewRoom(interviewRoomReq);
    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));

  }

}
