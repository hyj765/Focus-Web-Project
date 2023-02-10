package com.bb.focus.api.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 관련 API ([POST] /login) 요청에 필요한 리퀘스트 바디 정의.
 */

@Setter
@Getter
public class ServiceNoticeReq {

    @Getter
    @Setter
    public static class Create {

        @ApiModelProperty(name = "서비스 공지글의 카테고리")
        private Long categoryId;


        @ApiModelProperty(name = "서비스 공지글 제목")
        private String title;

        @ApiModelProperty(name = "서비스 공지글 내용")
        private String content;

    }


}
