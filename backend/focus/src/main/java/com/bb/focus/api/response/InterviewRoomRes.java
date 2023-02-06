package com.bb.focus.api.response;

import com.bb.focus.db.entity.interview.InterviewRoom;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel("EvaluatorRes")
@NoArgsConstructor
public class InterviewRoomRes implements Comparable<InterviewRoomRes> {

  @ApiModelProperty(name = "면접 일정 시퀀스 넘버", example = "1")
  private Long id;

  @ApiModelProperty(name = "면접 일정 이름", example = "홍길동")
  private String name;

  @ApiModelProperty(name = "면접 일정 시작 시간", example = "1111")
  private LocalDateTime startTime;

  @ApiModelProperty(name = "면접 일정 종료 시간", example = "개발부")
  private LocalDateTime endTime;

  @ApiModelProperty(name = "면접 날짜")
  private LocalDate date;

  @ApiModelProperty(name = "면접 일정 걸리는 시간(분)", example = "30")
  private int duration;

  public InterviewRoomRes(InterviewRoom interviewRoom) {
    id = interviewRoom.getId();
    name = interviewRoom.getName();
    startTime = interviewRoom.getStartTime();
    endTime = interviewRoom.getEndTime();
    duration = interviewRoom.getDuration();
    date = interviewRoom.getDate();
  }

  public InterviewRoomRes(Long id, String name, LocalDateTime startTime, LocalDateTime endTime,
      int duration) {
    this.id = id;
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.duration = duration;
  }

  @Override
  public int compareTo(InterviewRoomRes o) {
    return this.startTime.compareTo(o.startTime);
  }

}
