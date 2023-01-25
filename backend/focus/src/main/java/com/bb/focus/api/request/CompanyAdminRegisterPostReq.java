package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.vm.ci.meta.Local;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 기업 관리자 계정 생성 API
 */
@Getter
@Setter
@ApiModel("CompanyAdminRegisterPostRequest")
public class CompanyAdminRegisterPostReq {
    @ApiModelProperty(name = "기업 관리자 ID", example = "samsung")
    String userId;
    @ApiModelProperty(name = "기업 관리자 Password", example = "123")
    String pwd;
    @ApiModelProperty(name = "기업 이름", example = "삼성")
    String companyName;
    @ApiModelProperty(name = "계약 시작 날짜", example = "2022-01-23T17:43:02.945175")
    LocalDateTime startDate;
    @ApiModelProperty(name = "계약 종료 날짜", example = "2023-01-23T17:43:02.945175")
    LocalDateTime endDate;
    @ApiModelProperty(name = "기업 대표 전화 번호", example = "02-1234-1234")
    String tel;
    @ApiModelProperty(name = "기업 대표 이메일", example = "samsung@samsung.com")
    String email;
    @ApiModelProperty(name = "기업 관리자 이름", example = "이재용")
    String name;
    @ApiModelProperty(name = "기업 산업", example = "")
    String industry;
    @ApiModelProperty(name = "기업 규모", example = "")
    String size;
    @ApiModelProperty(name = "기업 로고 이미지", example = "")
    String logoImage;
    @ApiModelProperty(name = "기업이 정한 면접실 배경색", example = "")
    String skinColor;
    @ApiModelProperty(name = "유저 권한", example = "2")
    Byte userRole;
}
