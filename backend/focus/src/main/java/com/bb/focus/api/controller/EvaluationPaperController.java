package com.bb.focus.api.controller;

import com.bb.focus.api.request.EvaluationItemReq;
import com.bb.focus.api.response.EvaluationSheetItemRes;
import com.bb.focus.api.service.DataProcessService;
import com.bb.focus.api.service.EvaluationPaperService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/evaluation")
public class EvaluationPaperController {
    DataProcessService dataProcessService;
    EvaluationPaperService evaluationService;


    @Autowired
    public EvaluationPaperController(DataProcessService dService, EvaluationPaperService eService){
        dataProcessService = dService;
        evaluationService = eService;
    }



    @PostMapping("/sheets/{company-id}")
    public ResponseEntity<?> CreateSheet(@PathVariable(name="company-id")Long companyId,@RequestParam String sheet){
        evaluationService.CreateEvaluationSheet(companyId,sheet);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @ApiOperation(value="평가지 아이템 생성", notes="기업관리자로부터 입력받은 정보로 평가지를 생성한다.")
    @PostMapping("/sheets/items/{evaluation-sheet-id}")
    public ResponseEntity<?> CreateSheetItem(
            @PathVariable(name="evaluation-sheet-id")long sheetId,
            @RequestBody @ApiParam(required = true) EvaluationItemReq evaluationItemReq){

        evaluationService.CreateEvaluationItem(sheetId,evaluationItemReq);

        return new ResponseEntity<Void>(HttpStatus.OK);
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

}
