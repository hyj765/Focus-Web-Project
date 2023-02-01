package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("EvaluationResultReq")
public class EvaluationResultReq {

  @ApiModelProperty(name="지원자 id값") // 지원자가 누구인 지 확인이 가능함.
  Long ApplicantId;
  @ApiModelProperty(name="평가자 id값") // 평가자가 누구인 지 확인 가능함.
  Long EvaluatorId;
  @ApiModelProperty(name="면접 id값") // 몇차인 지 확인이 가능함.
  Long InterviewId;
  @ApiModelProperty(name="평가 결과", example = "전문성이 부족해보임 자료구조에 대한 이해도가 부족함.") // 평가자가 쓴 메모
  String Content;

}
