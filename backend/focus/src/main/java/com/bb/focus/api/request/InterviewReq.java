package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("InterviewReq")
public class InterviewReq {

  @ApiModelProperty(name = "차수", example = "1")
  private Byte step;

  @ApiModelProperty(name = "면접 이름", example = "두나무 FE1")
  private String name;

  @ApiModelProperty(name = "면접 시작 일시", example = "2021-11-09T11:44:30.327959")
  private LocalDateTime startDate;

  @ApiModelProperty(name = "면접 종료 일시", example = "2021-11-09T11:44:30.327959")
  private LocalDateTime endDate;

  @ApiModelProperty(name = "평가지 시퀀스 넘버", example = "1")
  private Long evaluationSheetId;

}
