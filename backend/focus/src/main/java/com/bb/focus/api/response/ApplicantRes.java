package com.bb.focus.api.response;

import com.bb.focus.db.entity.applicant.Applicant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ApplicantRes")
@NoArgsConstructor
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

  @ApiModelProperty(name="지원자 전화번호", example = "010-1235-5456")
  private String tel;

  @ApiModelProperty(name="지원자 이메일", example = "abc@gmail.com")
  private String email;

  public ApplicantRes(Applicant applicant){
    id = applicant.getId();
    name = applicant.getName();
    userId = applicant.getUserId();
    code = applicant.getCode();
    image = applicant.getCode();
  }

  public ApplicantRes(Long id, String name, String userId, String code, String image, String tel, String email) {
    this.id = id;
    this.name = name;
    this.userId = userId;
    this.code = code;
    this.image = image;
    this.tel = tel;
    this.email = email;
  }

  public static ApplicantRes of(Applicant user){
    ApplicantRes res = new ApplicantRes();
    res.setUserId(user.getUserId());
    res.setTel(user.getTel());
    res.setEmail(user.getEmail());
    res.setName(user.getName());
    return res;
  }

}
