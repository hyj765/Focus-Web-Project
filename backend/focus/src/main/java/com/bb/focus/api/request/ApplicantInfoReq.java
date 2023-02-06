package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 지원자 계정 생성 API ([POST] /companyusers/applicants) 요청에 필요한 리퀘스트 바디 정의
 */
@Getter
@Setter
@ApiModel("ApplicantInfoReq")
public class ApplicantInfoReq {

  @ApiModelProperty(name="지원자 이름", example = "홍길동")
  private String name;

  @ApiModelProperty(name="지원자 수험번호", example = "123456")
  private String code;

  @ApiModelProperty(name="지원자 성별] m: 남성, f: 여성", example = "f")
  private String gender;

  @ApiModelProperty(name="지원자 생일", example = "1998-08-01")
  private LocalDate birth;

  @ApiModelProperty(name="지원자 사진 url", example = "사진")
  private String image;

  @ApiModelProperty(name="지원자 이메일", example = "123@asd.com")
  private String email;

  @ApiModelProperty(name="지원자 휴대전화번호", example = "010-1111-2345")
  private String tel;

  @ApiModelProperty(name="지원자 이력서 url", example = "이력서")
  private String resume;

  @ApiModelProperty(name="지원자 학위] b: 학사, m: 석사, d: 박사", example = "b")
  private String degree;

  @ApiModelProperty(name="수상 횟수", example = "1")
  private Byte awardCount;

  @ApiModelProperty(name="대외활동 횟수", example = "2")
  private Byte activityCount;

  @ApiModelProperty(name="2년제 대학교 시퀀스 넘버", example = "1")
  private Long collegeId;

  @ApiModelProperty(name="4년제 대학교 시퀀스 넘버", example = "0")
  private Long univId;

  @ApiModelProperty(name="대학원 시퀀스 넘버", example = "0")
  private Long graduateId;

  @ApiModelProperty(name="전공", example = "컴퓨터공학과")
  private String major;
}
