package com.bb.focus.api.controller;

import com.bb.focus.api.request.EvaluationItemReq;
import com.bb.focus.api.request.EvaluationSheetReq;
import com.bb.focus.api.response.EvaluationSheetItemRes;
import com.bb.focus.api.service.DataProcessService;
import com.bb.focus.api.service.EvaluationPaperService;
import com.bb.focus.common.auth.FocusUserDetails;
import com.bb.focus.common.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "평가지 API", tags = {"EvaluationPaper"})
@RestController
@CrossOrigin("*")
@RequestMapping("/api/evaluation")
public class EvaluationPaperController {
    DataProcessService dataProcessService;
    EvaluationPaperService evaluationService;


    @Autowired
    public EvaluationPaperController(DataProcessService dService, EvaluationPaperService eService){
        dataProcessService = dService;
        evaluationService = eService;
    }



    @PostMapping("/sheets")
    public ResponseEntity<?> CreateSheet(
        @ApiIgnore Authentication authentication,
        @RequestBody @ApiParam(value = "평가지 이름", required = true) EvaluationSheetReq.Create evaluationSheetReq){

        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long companyAdminId = userDetails.getUser().getId();

        evaluationService.CreateEvaluationSheet(companyAdminId, evaluationSheetReq.getSheetName());
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
    @ApiOperation(value="평가지 아이템 생성", notes="기업관리자로부터 입력받은 정보로 평가지를 생성한다.")
    @PostMapping("/sheets/items/{evaluation-sheet-id}")
    public ResponseEntity<?> CreateSheetItem(
            @PathVariable(name="evaluation-sheet-id")long sheetId,
            @RequestBody @ApiParam(required = true) EvaluationItemReq evaluationItemReq){

        evaluationService.CreateEvaluationItem(sheetId,evaluationItemReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }



    // 조회
    //평가지만 보여주는 셀

    @GetMapping("/sheets/{evaluation-sheet-id}")
    public ResponseEntity<?> GetEvaluationSheetInfo(@PathVariable(name="evaluation-sheet-id") Long sheetId){
        // id -> evaluationsheet 호출 -> list<items> -> ....
        List<EvaluationSheetItemRes> evaluationSheetItemResList =evaluationService.GetEvaluationSheetItems(sheetId);
        if(evaluationSheetItemResList == null){
            return new ResponseEntity<String>("",HttpStatus.OK);
        }
        return new ResponseEntity<List<EvaluationSheetItemRes>>(evaluationSheetItemResList,HttpStatus.OK);
    }
    @GetMapping("/interview/{interview-room-id}")
    public ResponseEntity<?> GetInterviewRoomEvaluationItems(@PathVariable(name="interview-room-id")Long interviewRoomId ){

        List<EvaluationSheetItemRes> evaluationSheetItemResList=evaluationService.GetRoomPerEvaluationItems(interviewRoomId);

        if(evaluationSheetItemResList == null){
            return new ResponseEntity<String>("Data Error",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<EvaluationSheetItemRes>>(evaluationSheetItemResList,HttpStatus.OK);
    }

    // 결과까지 보여주는 함수
    @GetMapping("sheets/result")
    public ResponseEntity<?> GetEvaluationSheetResult(Long applicantId, Long processId){


        return null;
    }


    @PutMapping("/sheets/{evaluation-sheet-id}/{evaluation-item-id}")
    public ResponseEntity<?> ModifyEvaluationSheetInfo(@PathVariable(name="evaluation-sheet-id") Long sheetId, @PathVariable(name="evaluation-item-id") Long itemId){


        return null;
    }

    @PutMapping("/sheets/{evaluation-sheet-id}/{evaluation-sheet-item-id}")
    public ResponseEntity<?> ModifyEvaluationSheetItem(@PathVariable(name="evaluation-sheet-id") Long sheetId ,@PathVariable(name="evaluation-sheet-item-id") Long sheetItemId,@RequestParam EvaluationItemReq itemReq){

        return null;
    }

    // 삭제
    @DeleteMapping("/sheets/{evaluation-sheet-id}")
    public ResponseEntity<?> DeleteEvaluationSheet(@PathVariable(name="evaluation-sheet-id") Long sheetId)
    {
        if(evaluationService.RemoveEvaluationSheet(sheetId)){
            return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Delete Fail",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/sheets/deleteitem/{sheetitem-id}")
    public ResponseEntity<?> DeleteEvaluationSheetItem(@PathVariable(name="sheetitem-id") Long itemId){
        evaluationService.RemoveEvaluationItem(itemId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

}
