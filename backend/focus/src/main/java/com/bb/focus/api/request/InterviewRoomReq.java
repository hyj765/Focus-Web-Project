package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("InterviewRoomReq")
public class InterviewRoomReq {

  @ApiModelProperty(name = "면접실 이름", example = "두나무 FE1-1")
  private String name;

  @ApiModelProperty(name = "실제 면접 시작 시간", example = "")
  private LocalDateTime startTime;
  @ApiModelProperty(name = "실제 면접 종료 시간", example = "")
  private LocalDateTime endTime;


  @ApiModelProperty(name = "면접(차수) 시퀀스 넘버", example = "1")
  private Long interviewId;

  @ApiModelProperty(name = "해당 면접실에 들어가는 평가자 시퀀스 넘버 리스트", example = "")
  private Long[] evaluators;

  @ApiModelProperty(name = "해당 면접실에 들어가는 지원자 시퀀스 넘버 리스트", example = "")
  private Long[] applicants;
}
