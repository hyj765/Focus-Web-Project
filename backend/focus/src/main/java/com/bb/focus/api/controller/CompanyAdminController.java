package com.bb.focus.api.controller;

import com.bb.focus.api.request.EvaluatorInfoReq;
import com.bb.focus.api.response.EvaluatorDetailRes;
import com.bb.focus.api.response.EvaluatorRes;
import com.bb.focus.api.service.EvaluatorService;
import com.bb.focus.constants.BaseResponseBody;
import com.bb.focus.db.entity.evaluator.Evaluator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companyusers")
public class CompanyAdminController {

  private final EvaluatorService evaluatorService;

  @ApiOperation(value="평가자 계정 생성", notes="기업관리자로부터 입력받은 정보로 평가자 계정을 생성한다.")
  @PostMapping("/evaluators/{company-admin-id}")
  public ResponseEntity<? extends BaseResponseBody> createEvaluator(
      @PathVariable("company-admin-id") Long companyAdminId,
      @RequestBody @ApiParam(value="평가자 계정 생성 정보", required = true) EvaluatorInfoReq evaluatorInfo){

    evaluatorService.create(companyAdminId, evaluatorInfo);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value="평가자 ID, PWD 자동생성", notes = "평가자의 id와 pwd를 자동생성한다.")
  @PostMapping("/evaluators/create/{evaluator-id}")
  public ResponseEntity<? extends BaseResponseBody> autoSetEvaluatorAccount(@PathVariable("evaluator-id") Long id){

    evaluatorService.autoAssignAccount(id);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value="평가자 계정 수정", notes = "기업관리자로부터 입력받은 수정 정보로 평가자 계정을 수정한다.")
  @PutMapping("/evaluators/{evaluator-id}")
  public ResponseEntity<? extends BaseResponseBody> updateEvaluator(
      @PathVariable("evaluator-id") Long id,
      @RequestBody @ApiParam(value="평가자 계정 기본 정보", required = true) EvaluatorInfoReq evaluatorInfo){

    evaluatorService.updateEvaluatorInfo(id, evaluatorInfo);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "평가자 계정 삭제", notes = "기업관리자가 평가자 계정을 삭제한다.")
  @DeleteMapping("/evaluators/{evaluator-id}")
  public ResponseEntity<? extends BaseResponseBody> deleteEvaluator(@PathVariable("evaluator-id") Long id){

    evaluatorService.removeEvaluator(id);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "사내 평가자 계정 리스트 조회", notes = "사내 평가자 계정 리스트를 조회한다.")
  @GetMapping("/evaluators")
  public ResponseEntity<List<EvaluatorRes>> getEvaluators(){

    List<Evaluator> evaluators = evaluatorService.findAllEvaluators();

    List<EvaluatorRes> result = evaluators.stream()
        .map(e -> new EvaluatorRes(e))
        .collect(Collectors.toList());

    return ResponseEntity.status(200).body(result);
  }

  @ApiOperation(value = "평가자 상세 조회", notes = "특정 평가자 계정의 상세 정보를 조회한다.")
  @GetMapping("/evaluators/{evaluator-id}")
  public ResponseEntity<EvaluatorDetailRes> getEvaluatorDetail(@PathVariable("evaluator-id") Long id){

    Evaluator evaluator = evaluatorService.findEvaluator(id);

    EvaluatorDetailRes result = new EvaluatorDetailRes(evaluator);

    return ResponseEntity.status(200).body(result);
  }
}
