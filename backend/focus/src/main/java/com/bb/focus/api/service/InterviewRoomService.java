package com.bb.focus.api.service;

import com.bb.focus.api.request.InterviewRoomReq;
import com.bb.focus.db.entity.interview.InterviewRoom;

import java.util.Optional;

public interface InterviewRoomService {

  //면접 일정 생성
  public Long createInterviewRoom(InterviewRoomReq interviewRoomReq);

  //면접 일정 수정(기본 정보 수정)
  public Long updateInterviewRoom(Long interviewRoomId, InterviewRoomReq interviewRoomReq);

  //면접 일정 삭제
  public void removeInterviewRoom(Long interviewRoomId);

  //면접에 평가자 등록
  public void addEvaluatorToInterviewRoom(Long interviewRoomId, Long evaluatorId);

  //면접에 지원자 등록
  public void addApplicantToInterviewRoom(Long interviewRoomId, Long applicantId);

  //면접에서 평가자 삭제
  public void removeEvaluator(Long interviewRoomId, Long evaluatorId);

  //면접에서 지원자 삭제
  public void removeApplicant(Long interviewRoomId, Long applicantId);



  Optional<InterviewRoom> findById(Long id);
}
