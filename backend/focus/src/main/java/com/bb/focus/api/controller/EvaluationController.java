package com.bb.focus.api.controller;

import com.bb.focus.api.request.EvaluationApplicantReq;
import com.bb.focus.api.request.EvaluationItemInfoReq;
import com.bb.focus.api.request.InterviewResultReq;
import com.bb.focus.api.response.EvaluationSheetResultRes;
import com.bb.focus.api.response.InterviewRoomRes;
import com.bb.focus.api.service.DataProcessService;
import com.bb.focus.api.service.EvaluationService;
import com.bb.focus.common.auth.FocusUserDetails;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.applicant.Status;
import com.bb.focus.db.entity.helper.ApplicantEvaluator;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.bb.focus.db.repository.InterviewRoomRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "평가 API", tags = {"Evaluation"})
@RestController
@CrossOrigin("*")
@RequestMapping("/api/interview")
public class EvaluationController {

    EvaluationService evaluationService;
    DataProcessService dataProcessService;
    private final InterviewRoomRepository interviewRoomRepository;

    public EvaluationController(EvaluationService evaluationS
            , DataProcessService dataProcessS,
                                InterviewRoomRepository interviewRoomRepository) {
        evaluationService = evaluationS;
        dataProcessService = dataProcessS;

        this.interviewRoomRepository = interviewRoomRepository;
    }


    @ApiOperation(value = "자신이 평가한 지원자 조회", notes = "평가자는 이전에 자신이 평가한 지원자 정보를 읽어온다.")
    @GetMapping("/evaluation/{interview-id}/{evaluator-id}/{applicant-id}")
    public ResponseEntity<?> GetApplicantEvaluationInfo(@PathVariable(name = "interview-id") Long interviewId,
                                                        @PathVariable(name = "evaluator-id") Long evaluatorId
            , @PathVariable(name = "applicant-id") Long applicantId) {

        List<EvaluationSheetResultRes> evaluationSheetResultResList = evaluationService.findApplicantEvaluation(evaluatorId, applicantId, interviewId);

        if (evaluationSheetResultResList == null) {
            return new ResponseEntity<String>("해당 사용자의 평가정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<EvaluationSheetResultRes>>(evaluationSheetResultResList, HttpStatus.OK);

    }

    @ApiOperation(value = "평가자의 사용자 평가 기능", notes = "평가 시 사용될 API")
    @Transactional
    @PostMapping("/evaluation")
    public ResponseEntity<?> EvaluationApplicant(
            @RequestBody @Valid EvaluationApplicantReq evaluationApplicantReq, @ApiIgnore Authentication authentication
    ) {
        // evaluatorId 얻기
        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long evaluatorId = userDetails.getUser().getId();

        // applicantId, evaluatorId, interviewRoomId로 applicantEvaluatorId 찾기
        InterviewRoomRes interviewRoomRes = new InterviewRoomRes(interviewRoomRepository.findById(evaluationApplicantReq.getInterviewRoomId()).get());
        Long applicantEvaluatorId = 0L;
        System.out.println(interviewRoomRes.toString());
        for(ApplicantEvaluator ae : interviewRoomRes.getApplicantEvaluatorList()){
            if(ae.getApplicant().getId()==evaluationApplicantReq.getApplicantId()
            && ae.getEvaluator().getId()==evaluatorId){
                applicantEvaluatorId = ae.getId();
            }
        }

        // 평가 항목 결과들 저장
        for(EvaluationItemInfoReq eii : evaluationApplicantReq.getEvaluationItemInfoList()){
            evaluationService.ApplicantEvaluation(eii, applicantEvaluatorId, eii.getEvaluationItemId());
            evaluationService.UpdateApplicantEvaluationScore(applicantEvaluatorId);
        }

        // 평가 메모 저장
        evaluationService.UpdateApplicantEvaluationMemo(applicantEvaluatorId, evaluationApplicantReq.getMemo());

        return new ResponseEntity<String>("평가내역 저장완료", HttpStatus.OK);
    }

    @ApiOperation(value = "합불여부 체크", notes = "각 인터뷰 마지막에 합불여부를 결정하는 API")
    @Transactional
    @PostMapping("/decision/pass")
    public ResponseEntity<?> FinishInterview(@RequestBody @Valid List<InterviewResultReq> resultReq, @RequestBody @Valid Long processId) {

        for (int i = 0; i < resultReq.size(); ++i) {
            evaluationService.LoggingUserPass(processId
                    , resultReq.get(i).getApplicantId()
                    , Status.valueOf(resultReq.get(i).getPass()));
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "통계 테이블 갱신", notes = "현재 존재하는 사용자들에 대한 통계데이터 갱신")
    @Transactional
    @PostMapping("/staticstic/givestatistics")
    public ResponseEntity<?> UpdateStatistic(@RequestBody @Valid Long processId) {
        Map<String, Integer> major = dataProcessService.UpdateStatisticTable(processId);
        dataProcessService.CreateMajorTable(major, processId);

        return new ResponseEntity<String>("통계데이터 업데이트 성공", HttpStatus.OK);
    }


//  @ApiOperation(value = "평가내역 수정함수", notes = "평가내역 수정함수 decision/pass가 사용되기 전에만 가능")
//  @Transactional
//  @PutMapping("/modify/evaluation")
//  public ResponseEntity<?> ModifyApplicantEvaluation(@RequestBody @Valid EvaluationResultReq evaluationResultReq, @RequestBody @Valid Long applicantEvaluationId){
//
//    if(!evaluationService.ModifyApplicantEvaluation(evaluationResultReq)){
//      return new ResponseEntity<String>("수정에 실패하였습니다.",HttpStatus.BAD_REQUEST);
//    }
//    if(evaluationService.UpdateApplicantEvaluationScore(applicantEvaluationId)){
//      return new ResponseEntity<String>("총점 재계산 실패",HttpStatus.OK);
//    }
//
//    return new ResponseEntity<String>("수정 성공",HttpStatus.OK);
//  }


    @ApiOperation(value = "평가자의 평가 메모 내용 갱신", notes = "평가자의 평가 메모를 갱신해주는 API")
    @Transactional
    @PutMapping("/save/memo")
    public ResponseEntity<?> EvaluatorSaveMemo(@RequestBody @Valid Long applicantEvaluatorId, @RequestBody @Valid String memo) {
        // applicantevaluatorId와 메모
        if (!evaluationService.UpdateApplicantEvaluationMemo(applicantEvaluatorId, memo)) {
            return new ResponseEntity<String>("메모 갱신 실패", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("메모 저장 완료", HttpStatus.OK);
    }

}
