package com.bb.focus.api.controller;

import com.bb.focus.api.request.EvaluationData;
import com.bb.focus.api.request.EvaluationResultReq;
import com.bb.focus.api.service.EvaluationService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "평가 API", tags = {"Evaluation"})
@RestController
@CrossOrigin("*")
@RequestMapping("/interview")
public class EvaluationController {

  EvaluationService evaluationService;

  public EvaluationController(EvaluationService evaluationS){
    evaluationService = evaluationS;
  }


  @PostMapping("/evaluation")
  public ResponseEntity<?> EvaluationApplicant(
      @RequestBody EvaluationResultReq evaluationResultReq,
      @RequestBody EvaluationData evaluationData)
  {



    return null;
  }






}
