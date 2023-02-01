package com.bb.focus.api.controller;

import com.bb.focus.api.response.EvaluatorRes;
import com.bb.focus.api.service.EvaluatorService;
import com.bb.focus.common.auth.FocusUserDetails;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.bb.focus.db.entity.helper.ApplicantInterviewRoom;
import com.bb.focus.db.entity.helper.EvaluatorInterviewRoom;
import com.bb.focus.db.entity.interview.InterviewRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "평가자 API", tags = {"Evaluator"})
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

    @ApiOperation(value = "해당 평가자의 면접실 코드번호 조회")
    @GetMapping("/enter")
    public ResponseEntity<?> getRealRoomCode(
            @RequestBody @ApiParam(value = "면접 일정 시퀀스 넘버", required = true) Long evaluatorId) {

        return ResponseEntity.status(200).body(evaluatorId);
    }

    @ApiOperation(value = "날짜별로 해당 평가자에게 배정된 면접 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<List<InterviewRoom>> getInterviewRoomsInfo(@ApiIgnore Authentication authentication) {

        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long id = userDetails.getUser().getId();
        List<InterviewRoom> results = new ArrayList<>();
        Evaluator evaluator = evaluatorService.getEvaluatorById(id);
        List<EvaluatorInterviewRoom> evaluatorInterviewRoomList = evaluator.getEvaluatorInterviewRoomList();
        for(EvaluatorInterviewRoom eir : evaluatorInterviewRoomList){
            results.add(eir.getInterviewRoom());
        }
        // 위 코드는 해당 평가자에게 배정된 면접 일정 리스트 조회임
        // 날짜별로 면접 리스트 조회하려면
        // choice 1 : java stream 쓰기
        // choice 2 : native query 쓰기 -> mysql로 쿼리 짜봤으니까 그대로 복붙하면 되지 않을까..

        return ResponseEntity.status(200).body(results);
    }

    @ApiOperation(value = "해당 평가자에게 가장 가까운 면접 1개 정보 조회")
    @GetMapping("/next")
    public ResponseEntity<InterviewRoom> getNextInterviewRoomInfo(@ApiIgnore Authentication authentication) {

        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        InterviewRoom result = null;
        Long id = userDetails.getUser().getId();
        List<InterviewRoom> tmpResults = new ArrayList<>();
        Evaluator evaluator = evaluatorService.getEvaluatorById(id);
        List<EvaluatorInterviewRoom> evaluatorInterviewRoomList = evaluator.getEvaluatorInterviewRoomList();
        for(EvaluatorInterviewRoom eir : evaluatorInterviewRoomList){
            tmpResults.add(eir.getInterviewRoom());
        }
        // 위 코드는 해당 평가자에게 배정된 면접 일정 리스트 조회임
        // 현재 시간 기준으로 이후에 진행되는 면접 일정만 뽑은 다음에
        // 시간 빠른 순서대로 정렬하고
        // 맨 위에 있는 면접 일정 반환하면 됨
        // 이건 native query 위에꺼 성공하면 native query 쓰면 될 것 같고
        // 만약에 안되면.. queryDSL 한 번 써보고
        // 그래도 안되면 난몰라

        return ResponseEntity.status(200).body(result);
    }

    @ApiOperation(value = "해당 평가자에 배정된 면접별 지원자 리스트 조회")
    @GetMapping("/applicants")
    public ResponseEntity<?> getApplicantsInfo(@ApiIgnore Authentication authentication) {

        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long id = userDetails.getUser().getId();
        HashMap<Long, List<Applicant>> results = new HashMap<>();
        List<InterviewRoom> interviewRoomList = new ArrayList<>();
        Evaluator evaluator = evaluatorService.getEvaluatorById(id);
        List<EvaluatorInterviewRoom> evaluatorInterviewRoomList = evaluator.getEvaluatorInterviewRoomList();
        for(EvaluatorInterviewRoom eir : evaluatorInterviewRoomList){
            interviewRoomList.add(eir.getInterviewRoom());
        }
        for(InterviewRoom ir : interviewRoomList){
            List<ApplicantInterviewRoom> applicantInterviewRoomList = ir.getApplicantInterviewRoomList();
            Long key = ir.getId();
            List<Applicant> tmp = new ArrayList<>();
            for(ApplicantInterviewRoom air : applicantInterviewRoomList){
                tmp.add(air.getApplicant());
            }
            results.put(key, tmp);
        }

        return ResponseEntity.status(200).body(results);
    }


}
