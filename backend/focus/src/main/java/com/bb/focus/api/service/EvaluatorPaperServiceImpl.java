package com.bb.focus.api.service;

import com.bb.focus.db.entity.evaluation.EvaluationSheet;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class EvaluatorPaperServiceImpl implements  EvaluatorPaperService{

  public boolean CreateEvaluationSheet(){



    return false;
  }
  public List<EvaluationSheet> GetAllEvaluationSheet(){




    return null;
  }
  public EvaluationSheet GetEvaluationSheet(){




    return null;
  }
  public boolean DeleteEvaluationSheet(){





    return false;
  }
  public boolean ModifyEvalutionSheet(){




    return false;
  }

  public boolean addEvaluationItem(long sheetId, String scaleContent, byte scoreRange){



    return true;
  }

  public boolean addEvaluation_result(long sheetId){

    return false;
  }

}
