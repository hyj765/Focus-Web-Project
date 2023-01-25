package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 평가자 계정 생성 API ([POST] /companyusers/evaluators) 요청에 필요한 리퀘스트 바디 정의
 */
@Getter
@Setter
@ApiModel("EvaluatorRegisterPostReq")
public class EvaluatorInfoReq {

  @ApiModelProperty(name="평가자 이름", example = "홍길동")
  private String name;

  @ApiModelProperty(name="평가자 사번", example = "1111")
  private String code;

  @ApiModelProperty(name="평가자 부서", example = "개발부")
  private String department;

  @ApiModelProperty(name="평가자 직급", example = "사원")
  private String position;

  @ApiModelProperty(name="평가자 전화번호", example = "010-1111-2222")
  private String tel;

  @ApiModelProperty(name="평가자 이메일", example = "aaaa@bbb.com")
  private String email;

  @ApiModelProperty(name="평가자 사진", example = "사진 url")
  private String image;

}
