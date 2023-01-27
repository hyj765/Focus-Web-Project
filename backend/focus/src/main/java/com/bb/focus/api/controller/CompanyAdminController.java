package com.bb.focus.api.controller;

import com.bb.focus.api.request.ApplicantInfoReq;
import com.bb.focus.api.request.EvaluatorInfoReq;
import com.bb.focus.api.response.ApplicantDetailRes;
import com.bb.focus.api.response.ApplicantRes;
import com.bb.focus.api.response.CompanyAdminRes;
import com.bb.focus.api.response.EvaluatorDetailRes;
import com.bb.focus.api.response.EvaluatorRes;
import com.bb.focus.api.service.ApplicantService;
import com.bb.focus.api.service.CompanyAdminService;
import com.bb.focus.api.service.EvaluatorService;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.common.auth.FocusUserDetails;
import com.bb.focus.common.model.response.BaseResponseBody;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.company.CompanyAdmin;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/companyusers")
public class CompanyAdminController {

  private final EvaluatorService evaluatorService;
  private final ApplicantService applicantService;

  private final CompanyAdminService companyAdminService;

  @ApiOperation(value = "평가자 계정 생성", notes = "기업관리자로부터 입력받은 정보로 평가자 계정을 생성한다.")
  @PostMapping("/evaluators/{company-admin-id}")
  public ResponseEntity<?> createEvaluator(
      @PathVariable("company-admin-id") Long companyAdminId,
      @RequestBody @ApiParam(value = "평가자 계정 생성 정보", required = true) List<EvaluatorInfoReq> evaluatorInfoList) {

    for (EvaluatorInfoReq evaluator : evaluatorInfoList) {
      evaluatorService.create(companyAdminId, evaluator);
    }

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "지원자 계정 생성", notes = "기업관리자로부터 입력받은 정보로 지원자 계정을 생성한다.")
  @PostMapping("/applicants/{company-admin-id}")
  public ResponseEntity<? extends BaseResponseBody> createApplicant(
      @PathVariable("company-admin-id") Long companyAdminId,
      @RequestBody @ApiParam(value = "지원자 계정 생성 정보", required = true) List<ApplicantInfoReq> ApplicantInfoList) {

    for (ApplicantInfoReq applicant : ApplicantInfoList) {
      applicantService.create(companyAdminId, applicant);
    }

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "평가자 ID, PWD 자동생성 : 단건", notes = "평가자의 1명의 id와 pwd를 자동생성한다.")
  @PostMapping("/evaluators/create/{evaluator-id}")
  public ResponseEntity<? extends BaseResponseBody> autoSetEvaluatorAccount(
      @PathVariable("evaluator-id") Long id) {

    evaluatorService.autoAssignAccount(id);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "모든 평가자 ID, PWD 자동생성", notes = "사내 모든 평가자의 ID, PWD를 일괄 자동생성한다.")
  @PostMapping("/evaluators/create/all/{company-admin-id}")
  public ResponseEntity<? extends BaseResponseBody> autoSetTotalEvaluatorAccount(
      @PathVariable("company-admin-id") Long id) {

    List<Evaluator> evaluators = evaluatorService.findAllEvaluators(id);

    for (Evaluator evaluator : evaluators) {
      evaluatorService.autoAssignAccount(evaluator.getId());
    }
    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "지원자 ID, PWD 자동생성 : 단건", notes = "지원자의 1명의 id와 pwd를 자동생성한다.")
  @PostMapping("/applicants/create/{applicant-id}")
  public ResponseEntity<? extends BaseResponseBody> autoSetApplicantAccount(
      @PathVariable("applicant-id") Long id) {

    applicantService.autoAssignAccount(id);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  /**
   * company-admin-id @PathVariable로 사용한다.....
   */
  @ApiOperation(value = "모든 지원자 ID, PWD 자동생성", notes = "사내 모든 지원자의 ID, PWD를 일괄 자동생성한다.")
  @PostMapping("/applicants/create/all/{company-admin-id}")
  public ResponseEntity<? extends BaseResponseBody> autoSetTotalApplicantAccount(
      @PathVariable("company-admin-id") Long id) {

    List<Applicant> applicants = applicantService.findAllApplicants(id);

    for (Applicant applicant : applicants) {
      applicantService.autoAssignAccount(applicant.getId());
    }

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }


  @ApiOperation(value = "평가자 계정 수정", notes = "기업관리자로부터 입력받은 수정 정보로 평가자 계정을 수정한다.")
  @PutMapping("/evaluators/{evaluator-id}")
  public ResponseEntity<Map<String, Long>> updateEvaluator(
      @PathVariable("evaluator-id") Long id,
      @RequestBody @ApiParam(value = "평가자 계정 기본 정보", required = true) EvaluatorInfoReq evaluatorInfo) {

    evaluatorService.updateEvaluatorInfo(id, evaluatorInfo);

    Map<String, Long> result = new HashMap<>();
    result.put("id", id);

    return ResponseEntity.status(200).body(result);
  }

  @ApiOperation(value = "지원자 계정 수정", notes = "기업관리자로부터 입력받은 수정 정보로 지원자 계정을 수정한다.")
  @PutMapping("/applicants/{applicant-id}")
  public ResponseEntity<Map<String, Long>> updateApplicant(
      @PathVariable("applicant-id") Long id,
      @RequestBody @ApiParam(value = "지원자 계정 기본 정보", required = true) ApplicantInfoReq applicantInfo) {

    applicantService.updateApplicantInfo(id, applicantInfo);

    Map<String, Long> result = new HashMap<>();
    result.put("id", id);

    return ResponseEntity.status(200).body(result);
  }

  @ApiOperation(value = "평가자 계정 삭제", notes = "기업관리자가 평가자 계정을 삭제한다.")
  @DeleteMapping("/evaluators/{evaluator-id}")
  public ResponseEntity<? extends BaseResponseBody> deleteEvaluator(
      @PathVariable("evaluator-id") Long id) {

    evaluatorService.removeEvaluator(id);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "지원자 계정 삭제", notes = "기업관리자가 지원자 계정을 삭제한다.")
  @DeleteMapping("/applicants/{applicant-id}")
  public ResponseEntity<? extends BaseResponseBody> deleteApplicant(
      @PathVariable("applicant-id") Long id) {

    applicantService.removeApplicant(id);

    return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
  }

  @ApiOperation(value = "사내 평가자 계정 리스트 조회", notes = "사내 평가자 계정 리스트를 조회한다.")
  @GetMapping("/evaluators/{company-admin-id}/list/{search-name}")
  public ResponseEntity<Page<EvaluatorRes>> getEvaluators(
      @PathVariable("company-admin-id") Long id,
      @PathVariable(value = "search-name", required = false) Optional<String> search ,
      @PageableDefault(size=10, sort="code", direction = Direction.ASC) Pageable pageable) {

    Page<EvaluatorRes> evaluators =
        evaluatorService.findAllEvaluatorsUsePaging(pageable, String.valueOf(search), id);

    return ResponseEntity.status(200).body(evaluators);
  }

  @ApiOperation(value = "사내 지원자 계정 리스트 조회", notes = "사내 지원자 계정 리스트를 조회한다.")
  @GetMapping("/applicants/{company-admin-id}/list")
  public ResponseEntity<List<ApplicantRes>> getApplicants(
      @PathVariable("company-admin-id") Long id) {

    List<Applicant> applicants = applicantService.findAllApplicants(id);

    List<ApplicantRes> result = applicants.stream()
        .map(a -> new ApplicantRes(a))
        .collect(Collectors.toList());

    return ResponseEntity.status(200).body(result);
  }

  @ApiOperation(value = "평가자 상세 조회", notes = "특정 평가자 계정의 상세 정보를 조회한다.")
  @GetMapping("/evaluators/{evaluator-id}")
  public ResponseEntity<EvaluatorDetailRes> getEvaluatorDetail(
      @PathVariable("evaluator-id") Long id) {

    Evaluator evaluator = evaluatorService.findEvaluator(id);

    EvaluatorDetailRes result = new EvaluatorDetailRes(evaluator);

    return ResponseEntity.status(200).body(result);
  }

  @ApiOperation(value = "지원자 상세 조회", notes = "특정 지원자 계정의 상세 정보를 조회한다.")
  @GetMapping("/applicants/{applicant-id}")
  public ResponseEntity<ApplicantDetailRes> getApplicantDetail(
      @PathVariable("applicant-id") Long id) {

    Applicant applicant = applicantService.findApplicant(id);

    ApplicantDetailRes result = new ApplicantDetailRes(applicant);

    return ResponseEntity.status(200).body(result);
  }

  @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
  @GetMapping("/me")
  public ResponseEntity<CompanyAdminRes> getUserInfo(@ApiIgnore Authentication authentication) {
    /**
     * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
     * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
     */
    FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
    String userId = userDetails.getUsername();
    CompanyAdmin companyAdmin = companyAdminService.getCompanyAdminByUserId(userId);

    return ResponseEntity.status(200).body(CompanyAdminRes.of(companyAdmin));
  }

}
