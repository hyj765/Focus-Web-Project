package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API ([POST] /api/v1/auth/login) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserLoginPostRequest")
public class UserLoginPostReq {
    @ApiModelProperty(name = "유저 ID", example = "bb_cherry")
    String id;
    @ApiModelProperty(name = "유저 Password", example = "123")
    String password;
    @ApiModelProperty(name = "유저의 역할 식별자", example = "1")
    Byte userRole;

}
