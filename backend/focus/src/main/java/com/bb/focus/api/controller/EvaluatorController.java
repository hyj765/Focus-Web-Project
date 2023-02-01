package com.bb.focus.api.controller;

import com.bb.focus.api.response.ApplicantRes;
import com.bb.focus.api.response.EvaluatorRes;
import com.bb.focus.api.service.ApplicantService;
import com.bb.focus.api.service.EvaluatorService;
import com.bb.focus.common.auth.FocusUserDetails;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.evaluator.Evaluator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"Evaluator"})
@RestController
@RequestMapping("/evaluators")
public class EvaluatorController {

    @Autowired
    EvaluatorService evaluatorService;
    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
    @GetMapping("/me")
    public ResponseEntity<EvaluatorRes> getUserInfo(@ApiIgnore Authentication authentication) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        System.out.println("dkdkdkdkdkdkdk :  "+userDetails.getUsername());
        Evaluator evaluator = evaluatorService.getEvaluatorByUserId(userId);

        return ResponseEntity.status(200).body(EvaluatorRes.of(evaluator));
    }
}