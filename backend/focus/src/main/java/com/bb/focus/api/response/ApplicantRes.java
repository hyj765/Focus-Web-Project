package com.bb.focus.api.response;

import com.bb.focus.db.entity.applicant.Applicant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ApplicantRes")
public class ApplicantRes {
  @ApiModelProperty(name="지원자 시퀀스 넘버", example = "1")
  private Long id;

  @ApiModelProperty(name="지원자 이름", example = "김싸피")
  private String name;

  @ApiModelProperty(name="지원자 아이디", example = "xxxAxxxx")
  private String userId;

  @ApiModelProperty(name="지원자 수험번호", example = "123456")
  private String code;

  @ApiModelProperty(name="지원자 사진 url", example = "url")
  private String image;

  public ApplicantRes(Applicant applicant){
    id = applicant.getId();
    name = applicant.getName();
    userId = applicant.getUserId();
    code = applicant.getCode();
    image = applicant.getCode();
  }

}
