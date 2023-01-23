package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 서비스 관리자 계정 생성 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ServiceAdminRegisterPostRequest")
public class ServiceAdminRegisterPostReq {
	@ApiModelProperty(name="유저 ID", example="bb_bokhi")
	String id;
	@ApiModelProperty(name="유저 Password", example="123")
	String password;
	@ApiModelProperty(name="유저 이름", example="복희")
	String name;
	@ApiModelProperty(name="유저 전화 번호", example="010-1234-1234")
	String tel;
	@ApiModelProperty(name="유저 이메일", example="bokhi@gmail.com")
	String email;
	@ApiModelProperty(name="유저 권한", example="1")
	String userRole;
}
