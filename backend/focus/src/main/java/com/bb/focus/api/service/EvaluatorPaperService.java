package com.bb.focus.api.service;


import com.bb.focus.db.entity.evaluation.EvaluationSheet;
import java.util.List;

public interface EvaluatorPaperService {

      boolean CreateEvaluationSheet();
      List<EvaluationSheet> GetAllEvaluationSheet();
      EvaluationSheet GetEvaluationSheet();
      boolean DeleteEvaluationSheet();
      boolean ModifyEvalutionSheet();

}
