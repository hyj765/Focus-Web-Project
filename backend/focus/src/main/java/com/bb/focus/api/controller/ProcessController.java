package com.bb.focus.api.controller;

import com.bb.focus.api.request.ProcessReq;
import com.bb.focus.api.response.ProcessDetailRes;
import com.bb.focus.api.response.ProcessRes;
import com.bb.focus.api.service.ProcessService;
import com.bb.focus.common.auth.FocusUserDetails;
import com.bb.focus.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interview/process")
public class ProcessController {

  private final ProcessService processService;

  @ApiOperation(value = "프로세스 생성", notes = "기업관리자가 프로세스를 생성한다.")
  @PostMapping()
  public ResponseEntity<?> createProcess(
      @ApiIgnore Authentication authentication,
      @RequestBody @ApiParam(value = "프로세스 생성 정보", required = true) ProcessReq processReq) {
    FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
    Long companyAdminId = userDetails.getUser().getId();

    processService.createProcess(processReq, companyAdminId);
    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "프로세스 수정", notes = "기업관리자가 프로세스를 수정한다.")
  @PutMapping("/{process-id}")
  public ResponseEntity<Map<String, Long>> updateProcess(
      @ApiIgnore Authentication authentication,
      @PathVariable("process-id") Long id,
      @RequestBody @ApiParam(value = "프로세스 생성 정보", required = true) ProcessReq processReq) {

    processService.updateProcess(id, processReq);

    Map<String, Long> result = new HashMap<>();
    result.put("id", id);

    return ResponseEntity.status(200).body(result);
  }

  @ApiOperation(value = "프로세스 삭제", notes = "기업관리자가 프로세스를 삭제한다.")
  @DeleteMapping("/{process-id}")
  public ResponseEntity<? extends BaseResponseBody> deleteProcess(
      @ApiIgnore Authentication authentication,
      @PathVariable("process-id") Long id) {

    processService.removeProcess(id);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "예정된 프로세스 리스트 조회", notes = "기업의 예정된 프로세스 리스트를 조회한다.")
  @GetMapping()
  public ResponseEntity<List<ProcessRes>> getAllExpectedProcess(@ApiIgnore Authentication authentication) {

    FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
    Long companyAdminId = userDetails.getUser().getId();

    List<ProcessRes> expectedProcessList = processService.getAllExpectedProcess(companyAdminId);
    return ResponseEntity.status(200).body(expectedProcessList);
  }

//  @ApiOperation(value = "프로세스 상세 조회", notes = "프로세스의 상세 정보 조회 : n차 정보 포함")
//  @GetMapping("/{process-id}")
//  public ResponseEntity<ProcessDetailRes> getProcessDetail(
//      @ApiIgnore Authentication authentication,
//      @PathVariable("process-id") Long id){
//
//
//  }


}
