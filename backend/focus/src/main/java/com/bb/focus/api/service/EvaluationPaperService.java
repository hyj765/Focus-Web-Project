package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluationItemReq;
import com.bb.focus.api.response.EvaluationSheetItemRes;

import java.util.List;

public interface EvaluationPaperService {

    boolean CreateEvaluationSheet(Long companyId, String sheetInfo);
    boolean CreateEvaluationItem(Long sheetId, EvaluationItemReq evaluationItemReq);
    boolean RemoveEvaluationSheet(Long evaluationSheetId);

    boolean RemoveEvaluationItem(Long evaluationSheetItemId);
    List<EvaluationSheetItemRes> GetEvaluationSheetItems(Long evaluationSheetId);
    List<EvaluationSheetItemRes> GetRoomPerEvaluationItems(Long interviewRoomId);


}
