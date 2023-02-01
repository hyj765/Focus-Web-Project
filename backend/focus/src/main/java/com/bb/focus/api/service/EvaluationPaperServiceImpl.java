package com.bb.focus.api.service;

import com.bb.focus.api.request.EvaluationItemReq;
import com.bb.focus.api.response.EvaluationSheetItemRes;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.evaluation.EvaluationItem;
import com.bb.focus.db.entity.evaluation.EvaluationSheet;
import com.bb.focus.db.repository.CompanyAdminRepository;
import com.bb.focus.db.repository.EvaluationSheetItemRepository;
import com.bb.focus.db.repository.EvaluationSheetRepository;
import com.bb.focus.db.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationPaperServiceImpl implements EvaluationPaperService {

    EvaluationSheetRepository sheetRepo;
    CompanyAdminRepository companyRepo;
    EvaluationSheetItemRepository evaluationSheetItemRepo;
    ProcessRepository processRepo;
    @Autowired
    public EvaluationPaperServiceImpl(EvaluationSheetRepository sheetS, CompanyAdminRepository companyS, EvaluationSheetItemRepository evaluationItemS, ProcessRepository processS){
        sheetRepo=sheetS;
        companyRepo = companyS;
        evaluationSheetItemRepo = evaluationItemS;
        processRepo = processS;
    }

    //
    public void EvaluationApplicant(){

    }

    public void GetInterviewPerApplicant(){

    }

    public boolean CreateEvaluationSheet(Long companyId, String sheetInfo){
        EvaluationSheet evaluationSheet = new EvaluationSheet();
        CompanyAdmin companyAdmin = companyRepo.findById(companyId).orElseThrow(IllegalAccessError::new);
        companyAdmin.addEvaluationSheet(evaluationSheet);
        evaluationSheet.setName(sheetInfo);
        // validation check
        sheetRepo.save(evaluationSheet);

        return true;
    };
    public boolean CreateEvaluationItem(Long sheetId, EvaluationItemReq evaluationItemReq){
        EvaluationItem evaluationItem = new EvaluationItem();
        EvaluationSheet evaluationSheet = sheetRepo.findById(sheetId).orElseThrow(IllegalAccessError::new);
        evaluationItem.setScaleContent(evaluationItemReq.getContent());
        evaluationItem.setScoreRange(evaluationItemReq.getScore());
        evaluationSheet.addEvaluationItem(evaluationItem);
        // validation check
        evaluationSheetItemRepo.save(evaluationItem);
        return true;
    }
    public List<EvaluationSheetItemRes> GetEvaluationSheetItems(Long evaluationSheetId){
        EvaluationSheet evaluationSheet = sheetRepo.findById(evaluationSheetId).orElseThrow(IllegalAccessError::new);
        List<EvaluationItem> evaluationItemList = evaluationSheet.getEvaluationItemList();
        List<EvaluationSheetItemRes> evaluationSheetItemResList = new ArrayList<>();

        for(EvaluationItem item:evaluationItemList){
            evaluationSheetItemResList.add(new EvaluationSheetItemRes(item));
        }
        return evaluationSheetItemResList;
    }

    public boolean modifyEvaluationSheet(Long evaluationSheetId){




        return true;
    }
    public List<EvaluationSheetItemRes> GetRoomPerEvaluationItems(Long interviewRoomId){
        List<EvaluationSheetItemRes> evaluationSheetItemResList = null;
        // InterviewRoomRepo.findById(interviewRoomid);
        // interviewRoom.get(interviewId);
        // List<EvaluationSheetItemRes> evaluationSheetItemResList = GetEvaluationSheetItems(interview.getEvaluationSheetId);

        return null;
    }

    public boolean RemoveEvaluationSheet(Long evaluationSheetId){
        sheetRepo.deleteById(evaluationSheetId);
        return true;
    };

    public boolean RemoveEvaluationItem(Long evaluationSheetItemId){
        evaluationSheetItemRepo.deleteById(evaluationSheetItemId);

        return true;
    }


}