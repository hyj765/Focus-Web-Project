package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그아웃 API ([POST] /login) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserLogoutPostRequest")
public class UserLogoutPostReq {

//    @NotEmpty(message = "잘못된 요청입니다.")
    private String accessToken;

}
