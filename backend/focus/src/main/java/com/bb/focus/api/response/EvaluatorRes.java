package com.bb.focus.api.response;

import com.bb.focus.db.entity.evaluator.Evaluator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("EvaluatorRes")
public class EvaluatorRes {

  @ApiModelProperty(name="평가자 시퀀스 넘버", example = "1")
  private Long id;

  @ApiModelProperty(name="평가자 이름", example = "홍길동")
  private String name;

  @ApiModelProperty(name="평가자 사번", example = "1111")
  private String code;

  @ApiModelProperty(name="평가자 부서", example = "개발부")
  private String department;

  @ApiModelProperty(name="평가자 직급", example = "사원")
  private String position;

  @ApiModelProperty(name="평가자 사진", example = "사진 url")
  private String image;

  public EvaluatorRes(Evaluator evaluator) {
    id = evaluator.getId();
    name = evaluator.getName();
    code = evaluator.getCode();
    department = evaluator.getDepartment();
    position = evaluator.getPosition();
    image = evaluator.getImage();
  }
}
