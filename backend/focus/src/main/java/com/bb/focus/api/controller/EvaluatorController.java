package com.bb.focus.api.controller;

import com.bb.focus.api.response.ApplicantRes;
import com.bb.focus.api.response.EvaluatorRes;
import com.bb.focus.api.response.InterviewRoomRes;
import com.bb.focus.api.service.ApplicantService;
import com.bb.focus.api.service.EvaluatorService;
import com.bb.focus.common.auth.FocusUserDetails;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.evaluator.Evaluator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
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

        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long id = userDetails.getUser().getId();
        Evaluator evaluator = evaluatorService.getEvaluatorById(id);

        return ResponseEntity.status(200).body(EvaluatorRes.of(evaluator));
    }

    @ApiOperation(value = "날짜별로 해당 평가자에게 배정된 면접 리스트")
    @GetMapping("/list")
    public ResponseEntity<List<InterviewRoomRes>> getInterviewRoomsInfo(@ApiIgnore Authentication authentication) {

        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long id = userDetails.getUser().getId();
        List<InterviewRoomRes> interviewRoomResList = evaluatorService.getInterviewRoomsById(id);

        return ResponseEntity.status(200).body(interviewRoomResList);
    }

    @ApiOperation(value = "해당 평가자에게 가장 가까운 면접 1개 정보 조회")
    @GetMapping("/next")
    public ResponseEntity<EvaluatorRes> getNextInterviewRoomInfo(@ApiIgnore Authentication authentication) {
        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long id = userDetails.getUser().getId();
        Evaluator evaluator = evaluatorService.getEvaluatorById(id);

        return ResponseEntity.status(200).body(EvaluatorRes.of(evaluator));
    }

    @ApiOperation(value = "해당 평가자에 배정된 면접별 지원자 리스트 조회")
    @GetMapping("/applicants")
    public ResponseEntity<EvaluatorRes> getApplicantsInfo(@ApiIgnore Authentication authentication) {
        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long id = userDetails.getUser().getId();
        Evaluator evaluator = evaluatorService.getEvaluatorById(id);

        return ResponseEntity.status(200).body(EvaluatorRes.of(evaluator));
    }


}
