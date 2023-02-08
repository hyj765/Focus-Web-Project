package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluationItemReq;
import com.bb.focus.api.response.EvaluationSheetItemRes;

import java.util.List;

public interface EvaluationPaperService {



    Long CreateEvaluationSheet(Long companyId, String sheetInfo);
    boolean CreateEvaluationItem(Long sheetId, EvaluationItemReq evaluationItemReq);

    List<EvaluationSheetItemRes> GetEvaluationSheetItems(Long evaluationSheetId);
    List<EvaluationSheetItemRes> GetRoomPerEvaluationItems(Long interviewRoomId);
    boolean modifyEvaluationSheet(Long evaluationSheetId, String modifyName);
    boolean modifyUserEvaluationItem(Long evaluationSheetId, Long evaluationItemId, byte modifyRange, String modifyContent);
    boolean RemoveEvaluationItem(Long evaluationSheetItemId);
    boolean RemoveEvaluationSheet(Long evaluationSheetId);


}
