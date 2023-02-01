package com.bb.focus.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("EvaluationData")
public class EvaluationData {

  @ApiModelProperty(name="평가항목 id값") // 평가항목이 무엇인 지 확인이 가능함.
  Long id;

  @ApiModelProperty(name="근거") // 점수 주었을 때 근거가 무엇인 지
  String reason;

  @ApiModelProperty(name="점수") //해당 근거에 따른 정량적인 값
  String score;


}
