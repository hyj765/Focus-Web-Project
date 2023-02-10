package com.bb.focus.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * FAQ 관련 API ([POST] /login) 요청에 필요한 리퀘스트 바디 정의.
 */

public class FaqReq {

  @Getter
  @Setter
  public static class Create {

    @ApiModelProperty(name = "작성한 서비스 관리자 시퀀스 넘버", example = "1")
    private Long serviceAdminId;

    @ApiModelProperty(name = "faq 제목", example = "제목제목")
    private String title;

    @ApiModelProperty(name = "faq 내용", example = "내용내용")
    private String content;

    @ApiModelProperty(name = "3:기업관리자,평가자 열람 가능, 4:기업관리자,지원자 열람 가능", example = "3")
    private Byte authRange;


  }


}
